package todolist.usecase;

import com.furkanozdemir.todolist.port.TaskDto;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.GetListTasksHandler;
import com.furkanozdemir.todolist.usecase.model.ListTaskUseCase;
import com.furkanozdemir.todolist.usecase.model.response.TasksResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetListTasksHandlerTest {

    private TaskPort taskPort;

    private GetListTasksHandler handler;

    @BeforeEach
    void setUp() {
        taskPort = mock(TaskPort.class);
        handler = new GetListTasksHandler(taskPort);
    }

    @Test
    void handle_shouldReturnTasksResponseWithTasks() {
        String listId = "1";
        ListTaskUseCase useCase = new ListTaskUseCase(listId);

        List<TaskDto> mockTasks = List.of(new TaskDto("1", "t1", "d1", null, "1", null, null, null, null, List.of()),
                                          new TaskDto("2", "t2", "d2", null, "2", null, null, null, null, List.of()));

        when(taskPort.getAllTasksByListId(listId)).thenReturn(mockTasks);

        TasksResponse response = handler.handle(useCase);

        assertEquals(mockTasks, response.taskCollection());
        verify(taskPort, times(1)).getAllTasksByListId(listId);
    }
}
