package adityasingh.Solidarity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] WHITELIST_URL = {
            "/api/tasks/**",
            "/api/users/**"
//            "/hello",
//            "/alltasks",
//            "/addtask",
//            "/addUser",
//            "/addTaskToUser",
//            "/addUserToTask",
//            "/{id}/users",
//            "/{id}/tasks"
    };
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(WHITELIST_URL)
                .permitAll();

        return http.build();
    }
}
