package br.com.gestaoproducao.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService) throws Exception {
        JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(jwtService);

        http
                // Desativa CSRF, necessário para APIs
                .csrf(csrf -> csrf.disable())
                // Sem sessão: cada requisição precisa de JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Regras de autorização
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())

                // JWT filter antes do UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // Login via OAuth2 apenas para navegador
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // página de login do navegador
                        .successHandler((request, response, authentication) -> {
                            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                            String email = oAuth2User.getAttribute("email");

                            // Gera token JWT
                            String jwt = jwtService.generateToken(email);

                            // Redireciona pro frontend com o token
                            response.sendRedirect("http://localhost:3000/login/success?token=" + jwt);
                        }))

                // Tratamento de erro para APIs: retorna 401, não redireciona
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setContentType("application/json");
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                            // Monta JSON simples
                            String json = String.format(
                                    "{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"%s\",\"path\":\"%s\"}",
                                    "Token invalido ou ausente",
                                    request.getRequestURI());

                            response.getWriter().write(json);
                        }))
                // Logout
                .logout(logout -> logout
                        .logoutSuccessUrl("/public/logout-success").permitAll());

        return http.build();
    }
}
