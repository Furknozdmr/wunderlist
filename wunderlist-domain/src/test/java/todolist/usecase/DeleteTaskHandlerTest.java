package todolist.usecase;

import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.DeleteTaskHandler;
import com.furkanozdemir.todolist.usecase.model.DeleteTaskUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteTaskHandlerTest {

    private TaskPort taskPort;

    private DeleteTaskHandler handler;

    @BeforeEach
    void setUp() {
        taskPort = mock(TaskPort.class);
        handler = new DeleteTaskHandler(taskPort);
    }

    @Test
    void handle_shouldCallDeleteTaskByIdWithCorrectId() {
        String taskId = "1";
        DeleteTaskUseCase useCase = new DeleteTaskUseCase(taskId);

        handler.handle(useCase);

        verify(taskPort, times(1)).deleteTaskById(taskId);
    }
}
