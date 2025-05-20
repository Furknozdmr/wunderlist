package com.furkanozdemir.authorization.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.exception.InvalidEmailFormatException;
import com.furkanozdemir.common.exception.UserAlreadyExistException;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.common.usecase.model.NewUserUseCase;
import com.furkanozdemir.user.model.UserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@DomainComponent
@RequiredArgsConstructor
public class CreateUserHandler implements VoidUseCaseHandler<NewUserUseCase> {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private final UserDetailPort userDetailPort;

    @Override
    public void handle(NewUserUseCase useCase) {
        validateEmail(useCase.email());
        Optional<UserDto> userDtoByEmail = userDetailPort.getUserDtoByEmail(useCase.email());
        userDtoByEmail.ifPresent(__ -> {
            throw new UserAlreadyExistException(useCase.email());
        });
        userDetailPort.createUser(useCase.email(), useCase.name(), useCase.surname(), useCase.password());
    }

    private static void validateEmail(String email) {
            if (isNull(email) || email.isBlank()) {
                throw new InvalidEmailFormatException();
            }
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            if (!matcher.matches()) {
                throw new InvalidEmailFormatException();
            }
    }

}
