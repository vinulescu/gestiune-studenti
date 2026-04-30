package ro.facultate.gestiune_studenti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login").permitAll() // REQ-1: Pagina Acasă și Login sunt publice
                .anyRequest().authenticated() // REQ-4: Restul paginilor necesită autentificare
            )
            .formLogin(form -> form
                .loginPage("/login") // Am creat o pagină specială de login (REQ-9)
                .defaultSuccessUrl("/", true) // După login, te duce pe Acasă
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") // REQ-10: Funcția de logout
                .permitAll()
            );
        
        http.csrf(csrf -> csrf.disable()); // Simplificare pentru formularele noastre
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // standardul modern de criptare!
    }
}