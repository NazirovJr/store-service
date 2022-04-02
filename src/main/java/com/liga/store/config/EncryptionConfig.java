package com.liga.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Encoder configuration class.
 * Marked with @Configuration annotation - the class is the source of the bean definition.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 */
@Configuration
public class EncryptionConfig {

    /**
     * Implementation of PasswordEncoder that uses the BCrypt strong hashing function.
     *
     * @return strength the log rounds to use, between 4 and 31.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}