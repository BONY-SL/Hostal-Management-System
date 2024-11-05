package com.hostalmanagement.Web.Application.webconfig;
import com.hostalmanagement.Web.Application.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtAuthFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "**", "/css/**", "/js/**", "/images/**").permitAll()

                        .requestMatchers("/hostalmanage/auth/**")
                        .permitAll()
                        .requestMatchers("/hostalmanage/admin/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers("/hostalmanage/student/**").hasAuthority(Role.STUDENT.name())
                        .requestMatchers("/hostalmanage/warden/**").hasAuthority(Role.WARDEN.name())
                        .requestMatchers("/hostalmanage/subwarden/**").hasAuthority(Role.SUB_WARDEN.name())
                        .requestMatchers("/hostalmanage/maintainsupervisor/**").hasAuthority(Role.MAINTAIN_SUPERVISOR.name())
                        .requestMatchers("/hostalmanage/dean/**").hasAuthority(Role.DEAN.name())
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/hostalmanage/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

        return httpSecurity.build();
    }
}