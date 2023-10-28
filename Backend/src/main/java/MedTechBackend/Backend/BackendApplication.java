package MedTechBackend.Backend;

import MedTechBackend.Backend.security.auth.AuthenticationService;
import MedTechBackend.Backend.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static MedTechBackend.Backend.security.user.Role.ADMIN;
import static MedTechBackend.Backend.security.user.Role.DOCTOR;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	}

