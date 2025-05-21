package authorization.usecase;

import com.furkanozdemir.authorization.usecase.CreateUserHandler;
import com.furkanozdemir.common.exception.InvalidEmailFormatException;
import com.furkanozdemir.common.exception.UserAlreadyExistException;
import com.furkanozdemir.common.usecase.model.NewUserUseCase;
import com.furkanozdemir.user.model.UserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateUserHandlerTest {

    private UserDetailPort userDetailPort;

    private CreateUserHandler createUserHandler;

    @BeforeEach
    void setUp() {
        userDetailPort = mock(UserDetailPort.class);
        createUserHandler = new CreateUserHandler(userDetailPort);
    }

    @Test
    void shouldCreateUserWhenEmailIsValidAndNotExist() {
        NewUserUseCase useCase = new NewUserUseCase("Furkan", "Özdemir", "furknozdmr@hotmail.com", "password123");

        when(userDetailPort.getUserDtoByEmail("furknozdmr@hotmail.com")).thenReturn(Optional.empty());

        createUserHandler.handle(useCase);

        verify(userDetailPort, times(1)).createUser("furknozdmr@hotmail.com", "Furkan", "Özdemir", "password123");
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        NewUserUseCase useCase = new NewUserUseCase("Furkan", "Özdemir", "invalid-email", "password123");

        assertThrows(InvalidEmailFormatException.class, () -> createUserHandler.handle(useCase));
        verify(userDetailPort, never()).createUser(any(), any(), any(), any());
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        NewUserUseCase useCase = new NewUserUseCase("Furkan", "Özdemir", "furknozdmr@hotmail.com", "password123");

        when(userDetailPort.getUserDtoByEmail("furknozdmr@hotmail.com")).thenReturn(
                Optional.of(new UserDto("id", "Furkan", "Özdemir", "furknozdmr@hotmail.com", "hashedPass")));

        assertThrows(UserAlreadyExistException.class, () -> createUserHandler.handle(useCase));
        verify(userDetailPort, never()).createUser(any(), any(), any(), any());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        NewUserUseCase useCase = new NewUserUseCase("Furkan", "Özdemir", null, "password123");

        assertThrows(InvalidEmailFormatException.class, () -> createUserHandler.handle(useCase));
        verify(userDetailPort, never()).createUser(any(), any(), any(), any());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsBlank() {
        NewUserUseCase useCase = new NewUserUseCase("Furkan", "Özdemir", "", "password123");

        assertThrows(InvalidEmailFormatException.class, () -> createUserHandler.handle(useCase));
        verify(userDetailPort, never()).createUser(any(), any(), any(), any());
    }
}
