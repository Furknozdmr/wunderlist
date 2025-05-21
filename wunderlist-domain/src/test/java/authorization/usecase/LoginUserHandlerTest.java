package authorization.usecase;

import com.furkanozdemir.authorization.port.JwtPort;
import com.furkanozdemir.authorization.usecase.LoginUserHandler;
import com.furkanozdemir.authorization.usecase.model.LoginUserUseCase;
import com.furkanozdemir.authorization.usecase.model.response.UserInfoResponse;
import com.furkanozdemir.common.exception.UserInvalidLoginException;
import com.furkanozdemir.common.exception.UserNotFoundException;
import com.furkanozdemir.user.model.UserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LoginUserHandlerTest {

    private JwtPort jwtPort;

    private UserDetailPort userDetailPort;

    private LoginUserHandler loginUserHandler;

    @BeforeEach
    void setUp() {
        jwtPort = mock(JwtPort.class);
        userDetailPort = mock(UserDetailPort.class);
        loginUserHandler = new LoginUserHandler(jwtPort, userDetailPort);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
        String email = "notfound@example.com";
        LoginUserUseCase useCase = new LoginUserUseCase(email, "password");

        when(userDetailPort.getUserDtoByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> loginUserHandler.handle(useCase));
        verify(jwtPort, never()).isMatchPasswords(anyString(), anyString());
    }

    @Test
    void shouldThrowUserInvalidLoginExceptionWhenPasswordDoesNotMatch() {
        String email = "furknozdmr@hotmail.com";
        String inputPassword = "123";
        UserDto userDto = new UserDto("1", "Furkan", "Özdemir", email, "321");
        LoginUserUseCase useCase = new LoginUserUseCase(email, inputPassword);

        when(userDetailPort.getUserDtoByEmail(email)).thenReturn(Optional.of(userDto));
        when(jwtPort.isMatchPasswords(inputPassword, userDto.password())).thenReturn(false);

        assertThrows(UserInvalidLoginException.class, () -> loginUserHandler.handle(useCase));
        verify(jwtPort).isMatchPasswords(inputPassword, userDto.password());
        verify(jwtPort, never()).generateToken(anyString());
    }

    @Test
    void shouldReturnUserInfoResponseWhenLoginSuccessful() {
        String email = "furknozdmr@hotmail.com";
        String inputPassword = "123";
        UserDto userDto = new UserDto("1", "Furkan", "Özdemir", email, "321");
        LoginUserUseCase useCase = new LoginUserUseCase(email, inputPassword);

        when(userDetailPort.getUserDtoByEmail(email)).thenReturn(Optional.of(userDto));
        when(jwtPort.isMatchPasswords(inputPassword, userDto.password())).thenReturn(true);
        when(jwtPort.generateToken(email)).thenReturn("token");
        when(jwtPort.generateRefreshToken(email)).thenReturn("refreshToken");

        UserInfoResponse response = loginUserHandler.handle(useCase);

        assertNotNull(response);
        assertEquals("token", response.token());
        assertEquals("refreshToken", response.refreshToken());
        assertEquals("1", response.userId());

        verify(jwtPort).generateToken(email);
        verify(jwtPort).generateRefreshToken(email);
    }
}

