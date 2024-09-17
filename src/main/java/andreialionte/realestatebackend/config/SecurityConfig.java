package andreialionte.realestatebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // enable CORS and disable CSRF
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())

                // allow all requests without authentication
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()
                );

        // Disable the default Spring Security login form
        http.formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
