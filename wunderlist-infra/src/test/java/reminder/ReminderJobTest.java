package reminder;

import com.furkanozdemir.adapter.reminder.ReminderAdapter;
import com.furkanozdemir.adapter.reminder.ReminderJob;
import com.furkanozdemir.adapter.todolist.entity.AssignUser;
import com.furkanozdemir.adapter.todolist.entity.Task;
import com.furkanozdemir.adapter.todolist.repository.TaskRepository;
import com.furkanozdemir.common.enums.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReminderJobTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ReminderAdapter reminderAdapter;

    @InjectMocks
    private ReminderJob reminderJob;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        AssignUser assignUser = new AssignUser("1", "test@example.com", "Furkan", "Özdemir");
        task = Task.builder()
                   .id("task-1")
                   .title("Test Task")
                   .description("Reminder test task.")
                   .assignUser(assignUser)
                   .createdBy("creator-user")
                   .createdAt(LocalDateTime.now())
                   .status(TaskStatus.PENDING)
                   .deadline(LocalDate.now().plusDays(5))
                   .reminderDate(LocalDate.now().minusDays(1))
                   .build();
    }

    @Test
    void shouldTriggerReminderForTasksBeforeToday() {
        when(taskRepository.findTasksByReminderDateBefore(LocalDate.now())).thenReturn(List.of(task));

        reminderJob.checkReminders();

        verify(reminderAdapter).triggerReminder(task);
        verify(taskRepository).save(argThat(savedTask -> savedTask.getReminderDate() == null));
    }
}

