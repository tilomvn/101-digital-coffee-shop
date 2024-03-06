package com.interview.project.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Getter
public class JWTProvider {

    private final String ROLE_KEY = "roles";

    private final String SECRET;

    /**
     * Millisecond
     */
    private final long EXPIRE;

    private final String TOKEN_PREFIX;

    public JWTProvider(@Value("${security.secret-key}") String SECRET,
                       @Value("${security.expire}") long EXPIRE,
                       @Value("${security.token-prefix}") String TOKEN_PREFIX) {
        this.SECRET = SECRET;
        this.EXPIRE = EXPIRE;
        this.TOKEN_PREFIX = TOKEN_PREFIX;
    }

    public String generate(String id) {
        return JWT.create()
                .withSubject(id)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .sign(Algorithm.HMAC256(SECRET.getBytes()));
    }

    public String generate(String id, String roles) {
        return JWT.create()
                .withSubject(id)
                .withClaim(ROLE_KEY, roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .sign(Algorithm.HMAC256(SECRET.getBytes()));
    }

    public String verify(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX + " ", ""))
                .getSubject();
    }

    public DecodedToken getPayload(String token) {
        var decoded = JWT.require(Algorithm.HMAC256(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX + " ", ""));
        return DecodedToken.builder()
                .subject(decoded.getSubject()).roles(decoded.getClaim(ROLE_KEY).asString())
                .build();
    }

    @Builder
    @Getter
    public static class DecodedToken {
        String subject;
        String roles;
    }
}
