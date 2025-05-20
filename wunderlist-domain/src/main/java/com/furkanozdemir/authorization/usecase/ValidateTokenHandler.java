package com.furkanozdemir.authorization.usecase;

import com.furkanozdemir.authorization.port.JwtPort;
import com.furkanozdemir.authorization.usecase.model.ValidateTokenUseCase;
import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.BooleanUseCaseHandler;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class ValidateTokenHandler implements BooleanUseCaseHandler<ValidateTokenUseCase> {

    private final JwtPort jwtPort;

    @Override
    public Boolean handle(ValidateTokenUseCase useCase) {
    return jwtPort.validateToken(useCase.token());
    }
}
