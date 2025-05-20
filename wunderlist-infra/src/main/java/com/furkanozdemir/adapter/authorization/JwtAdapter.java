package com.furkanozdemir.adapter.authorization;

import com.furkanozdemir.authorization.port.JwtPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtAdapter implements JwtPort {

    private static String secret;

    @Value("${jwt.accessTokenExpiration}")
    private Long accessTokenExpiration;

    @Value("${jwt.refreshTokenExpiration}")
    private Long refreshTokenExpiration;

    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    public void setSecret(String secretKey) {
        JwtAdapter.secret = secretKey;
    }

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
      return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean validateToken(String token) {
        return isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String token(String email) {
        return Jwts
                .builder()
                .setClaims(new HashMap<>())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private String refreshToken(String email) {
        return Jwts
                .builder()
                .setClaims(new HashMap<>())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }
}
