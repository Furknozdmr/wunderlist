package todolist.usecase;

import com.furkanozdemir.todolist.port.TaskDto;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.GetUserTasksHandler;
import com.furkanozdemir.todolist.usecase.model.UserTaskUseCase;
import com.furkanozdemir.todolist.usecase.model.response.TasksResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetUserTasksHandlerTest {

    private TaskPort taskPort;

    private GetUserTasksHandler handler;

    @BeforeEach
    void setUp() {
        taskPort = mock(TaskPort.class);
        handler = new GetUserTasksHandler(taskPort);
    }

    @Test
    void handle_shouldReturnTasksResponseWithTasks() {
        String userId = "u1";
        UserTaskUseCase useCase = new UserTaskUseCase(userId);

        List<TaskDto> mockTasks = List.of(new TaskDto("1", "t1", "d1", null, "user1", null, null, null, null, List.of()),
                                          new TaskDto("2", "t2", "d2", null, "user1", null, null, null, null, List.of()));

        when(taskPort.getAllTasksByUserId(userId)).thenReturn(mockTasks);

        TasksResponse response = handler.handle(useCase);

        assertEquals(mockTasks, response.taskCollection());
        verify(taskPort, times(1)).getAllTasksByUserId(userId);
    }
}
