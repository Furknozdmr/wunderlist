package com.furkanozdemir.adapter.authorization;

import com.furkanozdemir.authorization.port.JwtPort;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtAdapter implements JwtPort {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.accessTokenExpiration}")
    private Long accessTokenExpiration;

    @Value("${jwt.refreshTokenExpiration}")
    private Long refreshTokenExpiration;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isMatchPasswords(String usecasePassword, String userPassword) {
        return passwordEncoder.matches(usecasePassword, userPassword);
    }

    @Override
    public String generateToken(String email) {
        return token(email);
        // TODO: Redise tokenları koy
    }

    @Override
    public String generateRefreshToken(String email) {
        return refreshToken(email);
        // TODO: Redise refresh-tokenları koy
    }

    @Override
    public String extractUserMailByToken(String token) {
        Map<String, ?> tokenBody = getTokenBody(token);
        return (String) tokenBody.get("sub");
    }

    private String token(String email) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + accessTokenExpiration);
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("typ", "JWT");
        headerClaims.put("alg", "HS512");

        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().subject(email).issuedAt(now).claims(headerClaims).expiration(expirationDate).signWith(secretKey).compact();
    }

    private String refreshToken(String email) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + refreshTokenExpiration);
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("typ", "JWT");
        headerClaims.put("alg", "HS512");

        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().subject(email).issuedAt(now).claims(headerClaims).expiration(expirationDate).signWith(secretKey).compact();
    }

    private Map<String, ?> getTokenBody(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        try {
            JwtParser build = Jwts.parser().verifyWith(secretKey).build();
            var payload = (Map<String, ?>) build.parse(token).getPayload();
            return payload;
        } catch (Exception e) {
            return Map.of();
        }
    }
}
