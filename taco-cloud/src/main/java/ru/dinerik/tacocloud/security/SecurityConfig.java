package ru.dinerik.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.dinerik.tacocloud.User;
import ru.dinerik.tacocloud.data.UserRepository;

// Базовый класс конфигурации для Spring Security
@Configuration
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
            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                    .requestMatchers("/design", "/orders").hasRole("USER")      // Разрешает доступ, если пользователь обладает указанной ролью
                    .requestMatchers("/", "/**").permitAll()        // Разрешает доступ всем без всяких условий

                .and()
                    .formLogin()            // настройка формы входа
                        .loginPage("/login")    // заменить встроенную страницу входа

                .and()
                    .logout()
                        .logoutSuccessUrl("/")

                .and()
                    .build();
    }
}