package ru.dinerik.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
}