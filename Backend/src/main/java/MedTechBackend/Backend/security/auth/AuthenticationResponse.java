package MedTechBackend.Backend.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("access_token_expiration")
    private long jwtExpiration;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("refresh_token_expiration")
    private long refreshExpiration;

}