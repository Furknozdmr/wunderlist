package com.furkanozdemir.adapter.authorization.rest;

import com.furkanozdemir.adapter.authorization.model.NewUserRequest;
import com.furkanozdemir.adapter.authorization.model.SignInRequest;
import com.furkanozdemir.authorization.usecase.model.*;
import com.furkanozdemir.authorization.usecase.model.response.RefreshTokenResponse;
import com.furkanozdemir.authorization.usecase.model.response.UserInfoResponse;
import com.furkanozdemir.common.usecase.BooleanUseCaseHandler;
import com.furkanozdemir.common.usecase.UseCaseHandler;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.common.usecase.model.NewUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthorizationController {

    private final VoidUseCaseHandler<NewUserUseCase> newUserUseCaseVoidUseCaseHandler;

    private final UseCaseHandler<UserInfoResponse, LoginUserUseCase> loginUserUseCaseHandler;

    private final VoidUseCaseHandler<LogoutUseCase> logoutUseCaseHandler;

    private final BooleanUseCaseHandler<ValidateTokenUseCase> validateTokenUseCaseHandler;

    private final UseCaseHandler<RefreshTokenResponse, RefreshTokenUseCase> refreshTokenUseCaseHandler;

    @PostMapping(path = "/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody NewUserRequest newUserRequest) {
        newUserUseCaseVoidUseCaseHandler.handle(
                new NewUserUseCase(newUserRequest.getName(), newUserRequest.getSurname(), newUserRequest.getEmail(), newUserRequest.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<UserInfoResponse> signIn(@RequestBody SignInRequest signInRequest) {
        var userInfoResponse = loginUserUseCaseHandler.handle(new LoginUserUseCase(signInRequest.getEmail(), signInRequest.getPassword()));
        return ResponseEntity.ok(userInfoResponse);
    }

    @PostMapping(path = "/sign-out")
    public ResponseEntity<Void> logout(@RequestHeader(value = "Authorization") String token,
                                       @RequestHeader(value = "Refresh-Token") String refreshToken) {
        logoutUseCaseHandler.handle(new LogoutUseCase(token, refreshToken));
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestHeader(value = "Authorization") String token) {
        Boolean isValid = validateTokenUseCaseHandler.handle(new ValidateTokenUseCase(token));
        return ResponseEntity.ok(isValid);
    }

    @PostMapping(path = "/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestHeader(value = "Refresh-Token") String refreshToken) {
        var response = refreshTokenUseCaseHandler.handle(new RefreshTokenUseCase(refreshToken));
        return ResponseEntity.ok(response);
    }
}
