package com.furkanozdemir.authorization.usecase;

import com.furkanozdemir.authorization.usecase.model.ValidateTokenUseCase;
import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class ValidateTokenHandler implements VoidUseCaseHandler<ValidateTokenUseCase> {

    @Override
    public void handle(ValidateTokenUseCase useCase) {

    }
}
