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

    @JsonProperty("accessToken")
    private String accessToken;
    @JsonProperty("accessExp")
    private long accessExp;
    @JsonProperty("refreshToken")
    private String refreshToken;
    @JsonProperty("refreshExp")
    private long refreshExp;

}