package MedTechBackend.Backend.security.token;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenProperties {

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public long getAccessExpiration() {
        return jwtExpiration;
    }

    public long getRefreshExpiration() {
        return refreshExpiration;
    }


}
