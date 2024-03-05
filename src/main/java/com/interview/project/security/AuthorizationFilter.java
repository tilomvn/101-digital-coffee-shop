package com.interview.project.security;

import com.interview.project.util.AESUtil;
import com.interview.project.util.JSON;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        try {
            var identityJson = request.getHeader(IDENTITY);
            var userEntity = JSON.fromJson(identityJson, UserIdentity.class);
            userEntity.setRoleList(Stream.of(AESUtil.newInstance().setSecretKey(ROLE_HASH_KEY).decrypt(userEntity.getRoles())
                    .split(ROLE_SEPARATOR))
                    .map(role -> SystemRole.valueOf(role)).collect(Collectors.toList()));
            ProfileLocal.set(userEntity);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Invalid User Identity:", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
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
