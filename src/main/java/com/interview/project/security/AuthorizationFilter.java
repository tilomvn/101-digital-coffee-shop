package com.interview.project.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * For security solution, user web filter to check valid access token in each request
 */
@WebFilter(value = "/*")
@Component
@AllArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        try {
            var token = request.getHeader(HttpHeaders.AUTHORIZATION);
            var userId = jwtProvider.verify(token);
            var userEntity = new UserIdentity(userId);
            ProfileLocal.set(userEntity);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Invalid User Identity:", e);
            response.sendRedirect("/security/unauthorized");
        } finally {
            ProfileLocal.clean();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        var requestURI = request.getRequestURI();
        return requestURI.contains("swagger-ui")
                || requestURI.contains("api-docs")
                || requestURI.contains("actuator")
                || requestURI.endsWith("/customer/login")
                || requestURI.endsWith("/security/unauthorized")
                || requestURI.contains("/h2-console");
    }
}