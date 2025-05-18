package com.furkanozdemir.authorization.usecase;

import com.furkanozdemir.authorization.usecase.model.LogoutUseCase;
import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class LogoutUserHandler implements VoidUseCaseHandler<LogoutUseCase> {
    @Override
    public void handle(LogoutUseCase useCase) {

    }
}
