package todolist.usecase;

import com.furkanozdemir.todolist.port.TodoListPort;
import com.furkanozdemir.todolist.usecase.DeleteTodoListHandler;
import com.furkanozdemir.todolist.usecase.model.DeleteTodoListUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteTodoListHandlerTest {

    private TodoListPort todoListPort;

    private DeleteTodoListHandler handler;

    @BeforeEach
    void setUp() {
        todoListPort = mock(TodoListPort.class);
        handler = new DeleteTodoListHandler(todoListPort);
    }

    @Test
    void handle_shouldCallDeleteTodoListByIdWithCorrectId() {
        String todoListId = "1";
        DeleteTodoListUseCase useCase = new DeleteTodoListUseCase(todoListId);

        handler.handle(useCase);

        verify(todoListPort, times(1)).deleteTodoListById(todoListId);
    }
}
