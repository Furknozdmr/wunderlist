package com.furkanozdemir.authorization.usecase;

import com.furkanozdemir.authorization.port.JwtPort;
import com.furkanozdemir.authorization.usecase.model.RefreshTokenUseCase;
import com.furkanozdemir.authorization.usecase.model.response.RefreshTokenResponse;
import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.UseCaseHandler;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class RefreshTokenHandler implements UseCaseHandler<RefreshTokenResponse, RefreshTokenUseCase> {

    private final JwtPort jwtPort;

    @Override
    public RefreshTokenResponse handle(RefreshTokenUseCase useCase) {
        var email = jwtPort.extractUserMailByToken(useCase.refreshToken());
        String token = jwtPort.generateToken(email);
        String refreshToken = jwtPort.generateRefreshToken(email);
        return new RefreshTokenResponse(token, refreshToken);
    }
}
