package reminder;

import com.furkanozdemir.adapter.reminder.ReminderAdapter;
import com.furkanozdemir.adapter.todolist.entity.AssignUser;
import com.furkanozdemir.adapter.todolist.entity.Task;
import com.furkanozdemir.common.enums.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ReminderAdapterTest {

    private ReminderAdapter reminderAdapter;

    @BeforeEach
    void setUp() {
        reminderAdapter = new ReminderAdapter();
    }

    @Test
    void shouldTriggerReminderSuccessfully() {
        AssignUser assignUser = new AssignUser("1", "test@example.com", "Furkan", "Özdemir");
        Task task = Task.builder()
                        .id("task-1")
                        .title("Test Task")
                        .description("This is a test task.")
                        .assignUser(assignUser)
                        .createdBy("creator-1")
                        .createdAt(LocalDateTime.now())
                        .status(TaskStatus.PENDING)
                        .deadline(LocalDate.now().plusDays(5))
                        .reminderDate(LocalDate.now().plusDays(1))
                        .build();

        // when & then
        assertDoesNotThrow(() -> reminderAdapter.triggerReminder(task));
    }
}
