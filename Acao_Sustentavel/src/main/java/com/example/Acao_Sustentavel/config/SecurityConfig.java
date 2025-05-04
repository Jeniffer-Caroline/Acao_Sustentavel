package com.example.Acao_Sustentavel.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.example.Acao_Sustentavel.handlers.CustomAccessDeniedHandler;
import com.example.Acao_Sustentavel.handlers.CustomAuthenticationEntryPoint;
import org.springframework.security.oauth2.jwt.JwtDecoders;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2ClientConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder() {
        String secret = "VY1YzY1AhAmQThkk4kR9sYTSvUG07cdWWfbQbn3TAXI=";
        byte[] keyBytes =
                Base64.getDecoder().decode(secret);
        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA265");
        return NimbusJwtDecoder.withSecretKey(key).build();
    }

    @Bean
    public SecretKey secretKey() {
        return new SecretKeySpec("sua-chave-secreta-aqui".getBytes(), "HmacSHA256");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt ->jwt.decoder(jwtDecoder())))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/api/admin/create").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/admin/edit").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/public").permitAll()
                        .requestMatchers("/api/user").authenticated().anyRequest().hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/api/admin/**").authenticated().anyRequest().hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/admin/delete").hasAuthority("ROLE_ADMIN"))

                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exceptionHandlingConfigurer -> {
                    CustomAuthenticationEntryPoint authenticationEntryPoint = new CustomAuthenticationEntryPoint() {
                        @Override
                        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
                            response.sendRedirect("/login");
                        }
                    };
                    CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler() {
                        @Override
                        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso negado");
                        }
                    };
                    exceptionHandlingConfigurer
                            .authenticationEntryPoint(authenticationEntryPoint)
                            .accessDeniedHandler(accessDeniedHandler);
                });

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .roles("ADMIN", "USER")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}