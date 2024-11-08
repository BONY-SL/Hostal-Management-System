package com.hostalmanagement.Web.Application.webconfig;

import com.hostalmanagement.Web.Application.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final TokenRepository tokenRepository;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        if(request.getServletPath().contains("/hostalmanage/auth")){

            filterChain.doFilter(request,response);
        }

        final String authHeader = request.getHeader("Authorization");

        final String jwt;

        final String userEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){

            filterChain.doFilter(request,response);

            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);

            if(jwtService.isTokenValid(jwt,userDetails) && isTokenValid){

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
        filterChain.doFilter(request,response);

    }
}
