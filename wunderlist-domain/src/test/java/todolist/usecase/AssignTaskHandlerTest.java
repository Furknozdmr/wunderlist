package todolist.usecase;

import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.AssignTaskHandler;
import com.furkanozdemir.todolist.usecase.model.AssignTaskUseCase;
import com.furkanozdemir.user.model.AssignUserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AssignTaskHandlerTest {

    private TaskPort taskPort;

    private UserDetailPort userDetailPort;

    private AssignTaskHandler assignTaskHandler;

    @BeforeEach
    void setUp() {
        taskPort = mock(TaskPort.class);
        userDetailPort = mock(UserDetailPort.class);
        assignTaskHandler = new AssignTaskHandler(taskPort, userDetailPort);
    }

    @Test
    void handle_shouldAssignTaskToUser() {
        String taskId = "2";
        String userEmail = "furknozdmr@hotmail.com";
        AssignTaskUseCase useCase = new AssignTaskUseCase(userEmail, taskId);

        AssignUserDto assignUserDto = new AssignUserDto("1", userEmail, "Furkan", "Özdemir");
        when(userDetailPort.getAssignUserDtoByEmail(userEmail)).thenReturn(assignUserDto);

        assignTaskHandler.handle(useCase);

        verify(userDetailPort, times(1)).getAssignUserDtoByEmail(userEmail);

        ArgumentCaptor<AssignUserDto> assignUserDtoCaptor = ArgumentCaptor.forClass(AssignUserDto.class);
        ArgumentCaptor<String> taskIdCaptor = ArgumentCaptor.forClass(String.class);

        verify(taskPort, times(1)).assignTask(taskIdCaptor.capture(), assignUserDtoCaptor.capture());

        assertEquals(taskId, taskIdCaptor.getValue());
        assertEquals(assignUserDto, assignUserDtoCaptor.getValue());
    }
}

