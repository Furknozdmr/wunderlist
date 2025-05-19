package com.furkanozdemir.authorization.usecase;

import com.furkanozdemir.authorization.port.JwtPort;
import com.furkanozdemir.authorization.usecase.model.LoginUserUseCase;
import com.furkanozdemir.authorization.usecase.model.response.UserInfoResponse;
import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.exception.UserInvalidLoginException;
import com.furkanozdemir.common.exception.UserNotFoundException;
import com.furkanozdemir.common.usecase.UseCaseHandler;
import com.furkanozdemir.user.port.UserDetailPort;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class LoginUserHandler implements UseCaseHandler<UserInfoResponse, LoginUserUseCase> {

    private final JwtPort jwtPort;

    private final UserDetailPort userDetailPort;

    @Override
    public UserInfoResponse handle(LoginUserUseCase useCase) {
        var user = userDetailPort.getUserDtoByEmail(useCase.email()).orElseThrow(() -> new UserNotFoundException(useCase.email()));
        if (jwtPort.isMatchPasswords(useCase.password(), user.password())) {
            String token = jwtPort.generateToken(user.email());
            String refreshToken = jwtPort.generateRefreshToken(user.email());
            return new UserInfoResponse(token, refreshToken, user.userId());
        } else {
            throw new UserInvalidLoginException();
        }
    }
}
