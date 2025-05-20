package com.furkanozdemir.authorization.port;

public interface JwtPort {

    boolean isMatchPasswords(String usecasePassword, String userPassword);

    String generateToken(String email);

    String generateRefreshToken(String email);

    String extractUserMailByToken(String token);

    boolean validateToken(String token);
}
