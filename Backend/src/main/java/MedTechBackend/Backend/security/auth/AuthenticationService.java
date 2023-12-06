package MedTechBackend.Backend.security.auth;


//import MedTechBackend.Backend.doctor.Doctor;
import MedTechBackend.Backend.entity.User.Role;
import MedTechBackend.Backend.security.token.Token;
import MedTechBackend.Backend.security.token.TokenProperties;
import MedTechBackend.Backend.security.token.TokenRepository;
import MedTechBackend.Backend.security.token.TokenType;
import MedTechBackend.Backend.entity.User.User;
import MedTechBackend.Backend.repository.User.UserRepository;
import MedTechBackend.Backend.service.Jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenProperties tokenProperties;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = repository.save(user);

//        if (request.getRole() == Role.DOCTOR) {
//            Doctor doctor = new Doctor();
//            doctor.setUser(savedUser); // Link the doctor to the user
////            // Set the properties for the doctor
////            doctor.setSpecialization(request.getSpecialization());
////            doctor.setExpYears(request.getExpYears());
//            savedUser.setDoctor(doctor); // Set the doctor to the user
//        }

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .accessExp(tokenProperties.getAccessExpiration())
                .refreshExp(tokenProperties.getRefreshExpiration())
                .userRole(user.getRole())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .accessExp(tokenProperties.getAccessExpiration())
                .refreshExp(tokenProperties.getRefreshExpiration())
                .userRole(user.getRole())
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public void createSuperAdmin() {
        if (repository.countAdmins() < 1) {
            User adminUser = new User();
            adminUser.setFirstname("Akarsh");
            adminUser.setLastname("Bhagat");
            adminUser.setEmail("admin@medtech.com");
            adminUser.setPassword(passwordEncoder.encode("admin11@"));
            adminUser.setRole(Role.ADMIN);

            repository.save(adminUser);
        }
    }

    public boolean deleteUserById(Integer id) {
        repository.deleteById(id);
        return true;
    }

    public void deleteAllUsers() {
        repository.deleteAll();
    }
}