package todolist.usecase;

import com.furkanozdemir.common.exception.UserNotFoundException;
import com.furkanozdemir.todolist.port.TodoListDto;
import com.furkanozdemir.todolist.port.TodoListPort;
import com.furkanozdemir.todolist.usecase.CreateTodoListHandler;
import com.furkanozdemir.todolist.usecase.model.CreateTodoListUseCase;
import com.furkanozdemir.user.model.UserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateTodoListHandlerTest {

    private TodoListPort todoListPort;

    private UserDetailPort userDetailPort;

    private CreateTodoListHandler handler;

    @BeforeEach
    void setUp() {
        todoListPort = mock(TodoListPort.class);
        userDetailPort = mock(UserDetailPort.class);
        handler = new CreateTodoListHandler(todoListPort, userDetailPort);
    }

    @Test
    void handle_shouldCreateTodoListWhenUserExists() {

        String userId = "u1";
        String title = "deneme";
        String description = "açıklama";

        CreateTodoListUseCase useCase = new CreateTodoListUseCase(userId, title, description);

        UserDto userDto = new UserDto(userId, "Furkan", "Özdemir", "furkan@hotmail.com", "123");
        when(userDetailPort.getUserDtoById(userId)).thenReturn(Optional.of(userDto));

        handler.handle(useCase);

        ArgumentCaptor<TodoListDto> captor = ArgumentCaptor.forClass(TodoListDto.class);
        verify(todoListPort).createTodoList(captor.capture());

        TodoListDto dto = captor.getValue();
        assertEquals(title, dto.title());
        assertEquals(description, dto.description());

        assertDoesNotThrow(() -> UUID.fromString(dto.id()));

        assertEquals(userId, dto.assignUser().userId());
        assertEquals("furkan@hotmail.com", dto.assignUser().email());
        assertEquals("Furkan", dto.assignUser().firstName());
        assertEquals("Özdemir", dto.assignUser().lastName());
    }

    @Test
    void handle_shouldThrowExceptionWhenUserNotFound() {

        String userId = "1";
        CreateTodoListUseCase useCase = new CreateTodoListUseCase(userId, "Deneme", "Açıklama");

        when(userDetailPort.getUserDtoById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> handler.handle(useCase));
        verify(todoListPort, never()).createTodoList(any());
    }
}
