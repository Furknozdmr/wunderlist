package todolist.usecase;

import com.furkanozdemir.common.enums.TaskStatus;
import com.furkanozdemir.todolist.port.TaskDto;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.CreateTaskHandler;
import com.furkanozdemir.todolist.usecase.model.CreateTaskUseCase;
import com.furkanozdemir.user.model.AssignUserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateTaskHandlerTest {

    private TaskPort taskPort;

    private UserDetailPort userDetailPort;

    private CreateTaskHandler handler;

    @BeforeEach
    void setUp() {
        taskPort = mock(TaskPort.class);
        userDetailPort = mock(UserDetailPort.class);
        handler = new CreateTaskHandler(taskPort, userDetailPort);
    }

    @Test
    void handle_shouldCreateTaskWithCorrectData() {
        String listId = "l1";
        String userId = "u1";
        String title = "deneme";
        String description = "açıklama";
        LocalDate deadline = LocalDate.of(2025, 6, 1);
        LocalDate reminderDate = LocalDate.of(2025, 5, 25);

        CreateTaskUseCase useCase = new CreateTaskUseCase(listId, userId, title, description, deadline, reminderDate);

        AssignUserDto assignUserDto = new AssignUserDto(userId, "furknozdmr@hotmail.com", "Furkan", "Özdemir");
        when(userDetailPort.getAssignUserDtoById(userId)).thenReturn(assignUserDto);

        handler.handle(useCase);

        ArgumentCaptor<TaskDto> captor = ArgumentCaptor.forClass(TaskDto.class);
        verify(taskPort).createTask(captor.capture());

        TaskDto task = captor.getValue();
        assertEquals(title, task.title());
        assertEquals(description, task.description());
        assertEquals(assignUserDto, task.assignUser());
        assertEquals(deadline, task.deadline());
        assertEquals(reminderDate, task.reminderDate());
        assertEquals(TaskStatus.PENDING, task.status());
        assertNotNull(task.createdAt());
        assertTrue(task.subTaskList().isEmpty());
    }
}
