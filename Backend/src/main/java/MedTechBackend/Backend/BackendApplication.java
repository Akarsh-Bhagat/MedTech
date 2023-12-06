package MedTechBackend.Backend;


import MedTechBackend.Backend.security.auth.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner initSuperAdmin(AuthenticationService authenticationService) {
		return args -> {
			authenticationService.createSuperAdmin();
		};
	}
	}

