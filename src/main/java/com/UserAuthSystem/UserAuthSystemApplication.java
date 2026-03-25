package com.UserAuthSystem;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootApplication
@EnableJpaAuditing
public class UserAuthSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthSystemApplication.class, args);
	}
	
	@Bean
	public AuditorAware<String> auditorProvider() {
	    return () -> Optional.ofNullable(
	            SecurityContextHolder.getContext().getAuthentication()
	    )
	    .filter(auth -> auth.isAuthenticated())
	    .map(auth -> {
	        Object principal = auth.getPrincipal();

	        if (principal instanceof UserDetails) {
	            return ((UserDetails) principal).getUsername();
	        } else if ("anonymousUser".equals(principal)) {
	            return "SYSTEM";
	        } else {
	            return principal.toString();
	        }
	    })
	    .or(() -> Optional.of("SYSTEM"));
	}

}
