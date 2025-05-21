package todolist.usecase;

import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.DeleteSubTaskHandler;
import com.furkanozdemir.todolist.usecase.model.DeleteSubTaskUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteSubTaskHandlerTest {

    private TaskPort taskPort;

    private DeleteSubTaskHandler handler;

    @BeforeEach
    void setUp() {
        taskPort = mock(TaskPort.class);
        handler = new DeleteSubTaskHandler(taskPort);
    }

    @Test
    void handle_shouldCallDeleteSubTaskByIdWithCorrectParameters() {

        String subTaskId = "1";
        String taskId = "2";
        DeleteSubTaskUseCase useCase = new DeleteSubTaskUseCase(subTaskId, taskId);

        handler.handle(useCase);

        verify(taskPort, times(1)).deleteSubTaskById(subTaskId, taskId);
    }
}
