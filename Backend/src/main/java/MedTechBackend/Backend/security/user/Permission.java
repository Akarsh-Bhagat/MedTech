package MedTechBackend.Backend.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    PERSON_READ("management:read"),
    PERSON_UPDATE("management:update"),
    PERSON_CREATE("management:create"),
    PERSON_DELETE("management:delete")

    ;

    @Getter
    private final String permission;
}