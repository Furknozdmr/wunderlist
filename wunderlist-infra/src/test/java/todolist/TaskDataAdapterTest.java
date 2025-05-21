package todolist;

import com.furkanozdemir.adapter.todolist.TaskDataAdapter;
import com.furkanozdemir.adapter.todolist.entity.Task;
import com.furkanozdemir.adapter.todolist.repository.TaskRepository;
import com.furkanozdemir.common.enums.TaskStatus;
import com.furkanozdemir.common.exception.TaskNotFoundException;
import com.furkanozdemir.common.mapper.TaskMapper;
import com.furkanozdemir.todolist.port.SubTaskDto;
import com.furkanozdemir.todolist.port.TaskDto;
import com.furkanozdemir.user.model.AssignUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class TaskDataAdapterTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskDataAdapter taskDataAdapter;

    private Task task;

    private TaskDto taskDto;

    public static TaskDto createSampleTaskDto() {
        AssignUserDto assignUser = new AssignUserDto("1", "furknozdmr@hotmail.com", "Furkan", "Özdemir");

        SubTaskDto subTask1 = new SubTaskDto("1", "deneme", "açıklama", TaskStatus.PENDING, LocalDate.of(2025, 5, 25), LocalDate.of(2025, 5, 20));
        SubTaskDto subTask2 = new SubTaskDto("1232", "3232", "desc", TaskStatus.IN_PROGRESS, LocalDate.of(2025, 6, 5), LocalDate.of(2025, 6, 1));

        return new TaskDto("taskId123", "deneme", "açıklama", assignUser, "2", LocalDateTime.now(), TaskStatus.PENDING, LocalDate.of(2025, 6, 15),
                           LocalDate.of(2025, 6, 10), List.of(subTask1, subTask2));
    }

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId("1");
        task.setTitle("Test Task");
        task.setStatus(TaskStatus.PENDING);

        taskDto = createSampleTaskDto();
    }

    @Test
    void shouldFindTaskById() {
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));
        when(taskMapper.toDto(task)).thenReturn(taskDto);

        TaskDto result = taskDataAdapter.findTaskById("1");

        assertNotNull(result);
        assertEquals("deneme", result.title());
        verify(taskRepository).findById("1");
        verify(taskMapper).toDto(task);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        when(taskRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskDataAdapter.findTaskById("1"));
        verify(taskRepository).findById("1");
    }

    @Test
    void shouldGetAllTasksByUserId() {
        List<Task> tasks = List.of(task);
        List<TaskDto> dtos = List.of(taskDto);

        when(taskRepository.findTasksByAssignUserUserId("3")).thenReturn(tasks);
        when(taskMapper.toDtoList(tasks)).thenReturn(dtos);

        List<TaskDto> result = taskDataAdapter.getAllTasksByUserId("3");

        assertEquals(1, result.size());
        verify(taskRepository).findTasksByAssignUserUserId("3");
    }

    @Test
    void shouldDeleteTaskById() {
        taskDataAdapter.deleteTaskById("1");
        verify(taskRepository).deleteById("1");
    }

    @Test
    void shouldCreateTask() {
        when(taskMapper.toEntity(taskDto)).thenReturn(task);

        taskDataAdapter.createTask(taskDto);

        verify(taskRepository).save(task);
    }

    @Test
    void shouldChangeTaskStatus() {
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));

        taskDataAdapter.changeTaskStatus("1", TaskStatus.COMPLETED);

        assertEquals(TaskStatus.COMPLETED, task.getStatus());
        verify(taskRepository).save(task);
    }

    @Test
    void shouldAssignTask() {
        AssignUserDto assignUserDto = new AssignUserDto("1", "furknozdmr@hotmail.com", "Furkan", "Özdemir");

        when(taskRepository.findById("1")).thenReturn(Optional.of(task));
        when(taskMapper.toEntity(assignUserDto)).thenReturn(
                new com.furkanozdemir.adapter.todolist.entity.AssignUser(assignUserDto.userId(), assignUserDto.email(), assignUserDto.firstName(),
                                                                         assignUserDto.lastName()));

        taskDataAdapter.assignTask("1", assignUserDto);

        assertNotNull(task.getAssignUser());
        verify(taskRepository).save(task);
    }
}
