package com.interview.project.security;

import com.interview.project.exception.ErrorInfo;
import com.interview.project.exception.SystemRuntimeException;
import com.interview.project.util.AESUtil;
import com.interview.project.util.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.interview.project.util.Constants.*;

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
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized");
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
                || requestURI.contains("/h2-console");
    }
}
