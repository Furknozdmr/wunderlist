package com.furkanozdemir.authorization.usecase;

import com.furkanozdemir.authorization.usecase.model.ValidateRefreshTokenUseCase;
import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class ValidateRefreshTokenHandler implements VoidUseCaseHandler<ValidateRefreshTokenUseCase> {

    @Override
    public void handle(ValidateRefreshTokenUseCase useCase) {

    }
}
