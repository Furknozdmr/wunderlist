package todolist.usecase;

import com.furkanozdemir.common.enums.TaskStatus;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.ChangeTaskStatusHandler;
import com.furkanozdemir.todolist.usecase.model.ChangeTaskStatusUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ChangeTaskStatusHandlerTest {

    @Mock
    private TaskPort taskPort;

    @InjectMocks
    private ChangeTaskStatusHandler changeTaskStatusHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handle_shouldCallChangeTaskStatus() {
        String taskId = "task1";
        String newStatus = TaskStatus.COMPLETED.toString();

        ChangeTaskStatusUseCase useCase = new ChangeTaskStatusUseCase(taskId, TaskStatus.COMPLETED);

        changeTaskStatusHandler.handle(useCase);

        verify(taskPort, times(1)).changeTaskStatus(taskId, TaskStatus.COMPLETED);
    }
}
