package io.sewah.springboot3springsecurity6demo.configs.security;

import io.sewah.springboot3springsecurity6demo.configs.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private static final String AUTHORIZATION_HEADER = "authorization_header";
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        final String jwtToken;
        final String userEmail;

        // check authorization
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //extract token from authorization
        jwtToken = authHeader.substring(7);
        userEmail = jwtUtils.getUsername(jwtToken);

        //verify if user is not already logged in
        if(userEmail !=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            //check token validity
            if(jwtUtils.isTokenValid(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // update security context
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }
        filterChain.doFilter(request, response);

    }
}
