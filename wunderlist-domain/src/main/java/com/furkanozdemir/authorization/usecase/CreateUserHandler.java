package com.furkanozdemir.authorization.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.exception.UserAlreadyExistException;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.common.usecase.model.NewUserUseCase;
import com.furkanozdemir.user.port.UserDetailPort;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class CreateUserHandler implements VoidUseCaseHandler<NewUserUseCase> {

    private final UserDetailPort userDetailPort;

    @Override
    public void handle(NewUserUseCase useCase) {
        userDetailPort.getUserDtoByEmail(useCase.email()).orElseThrow(() -> new UserAlreadyExistException(useCase.email()));
        userDetailPort.createUser(useCase.email(), useCase.name(), useCase.surname(), useCase.password());
    }

}
