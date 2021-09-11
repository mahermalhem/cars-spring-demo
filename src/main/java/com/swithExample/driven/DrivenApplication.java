package com.swithExample.driven;

import com.swithExample.driven.common.enums.AppStatus;
import com.swithExample.driven.common.enums.UserRole;
import com.swithExample.driven.models.User;
import com.swithExample.driven.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DrivenApplication implements CommandLineRunner {

    private final String DEFAULT_USER = "admin";
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    public static void main(String[] args) {

        SpringApplication.run(DrivenApplication.class, args);
        System.out.println("The application is running");

    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
//                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
//                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
//        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
//                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }

    @Override
    public void run(String... args) throws Exception {
        if (!repository.existsByUsername(DEFAULT_USER)) {
            final User user = new User();
            user.setRole(UserRole.ADMIN);
            user.setUsername(DEFAULT_USER);
            user.setPassword(encoder.encode("password"));
            user.setActive(true);
            user.setNotLocked(true);
            user.setEmail("philconal24@gmail.com");
            user.setStatus(AppStatus.ACTIVE);
            repository.save(user);
        }
    }
}
