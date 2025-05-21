package todolist.usecase;

import com.furkanozdemir.todolist.port.TodoListDto;
import com.furkanozdemir.todolist.port.TodoListPort;
import com.furkanozdemir.todolist.usecase.GetUserTodoListsHandler;
import com.furkanozdemir.todolist.usecase.model.TodoListUseCase;
import com.furkanozdemir.todolist.usecase.model.response.UserTodoListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetUserTodoListsHandlerTest {

    private TodoListPort todoListPort;

    private GetUserTodoListsHandler handler;

    @BeforeEach
    void setUp() {
        todoListPort = mock(TodoListPort.class);
        handler = new GetUserTodoListsHandler(todoListPort);
    }

    @Test
    void handle_shouldReturnUserTodoListResponseWithTodoLists() {
        String userId = "1";
        TodoListUseCase useCase = new TodoListUseCase(userId);

        List<TodoListDto> mockTodoLists = List.of(new TodoListDto("1", "l1", "d1", null), new TodoListDto("2", "l2", "d2", null));

        when(todoListPort.getAllTodoListsByUserId(userId)).thenReturn(mockTodoLists);

        UserTodoListResponse response = handler.handle(useCase);

        assertEquals(mockTodoLists, response.todoListCollection());
        verify(todoListPort, times(1)).getAllTodoListsByUserId(userId);
    }
}
