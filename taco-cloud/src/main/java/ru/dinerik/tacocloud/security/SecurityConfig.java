package ru.dinerik.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.dinerik.tacocloud.User;
import ru.dinerik.tacocloud.data.UserRepository;

// Базовый класс конфигурации для Spring Security
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();         // применяет надежное шифрование bcrypt
    }

    // Определение bean-компонента службы хранения учетных записей
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User " + username + " not found");
        };
    }
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                //.csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/design", "/orders").hasRole("USER")      // Разрешает доступ, если пользователь обладает указанной ролью
                .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                   .requestMatchers(HttpMethod.DELETE, "/api/ingredients").hasAuthority("SCOPE_deleteIngredients")
                    .requestMatchers("/", "/**").permitAll()        // Разрешает доступ всем без всяких условий
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).build();

    }
}