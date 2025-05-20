package todolist.entity;

import com.furkanozdemir.adapter.todolist.entity.SubTask;
import com.furkanozdemir.common.enums.TaskStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SubTaskTest {

    @Test
    void shouldCreateSubTaskWithAllArgsConstructor() {
        // Given
        String id = "1";
        String title = "Subtask Test";
        String description = "Deneme";
        TaskStatus status = TaskStatus.IN_PROGRESS;
        LocalDate deadline = LocalDate.of(2025, 6, 1);
        LocalDate reminder = LocalDate.of(2025, 5, 25);

        // When
        SubTask subTask = new SubTask(id, title, description, status, deadline, reminder);

        // Then
        assertThat(subTask.getSubTaskId()).isEqualTo(id);
        assertThat(subTask.getSubTaskTitle()).isEqualTo(title);
        assertThat(subTask.getSubTaskDescription()).isEqualTo(description);
        assertThat(subTask.getSubTaskStatus()).isEqualTo(status);
        assertThat(subTask.getSubTaskDeadline()).isEqualTo(deadline);
        assertThat(subTask.getSubTaskReminderDate()).isEqualTo(reminder);
    }

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        SubTask subTask = new SubTask();

        String id = "456";
        String title = "test2";
        String description = "Deneme2";
        TaskStatus status = TaskStatus.COMPLETED;
        LocalDate deadline = LocalDate.of(2025, 7, 15);
        LocalDate reminder = LocalDate.of(2025, 7, 10);

        // When
        subTask.setSubTaskId(id);
        subTask.setSubTaskTitle(title);
        subTask.setSubTaskDescription(description);
        subTask.setSubTaskStatus(status);
        subTask.setSubTaskDeadline(deadline);
        subTask.setSubTaskReminderDate(reminder);

        // Then
        assertThat(subTask.getSubTaskId()).isEqualTo(id);
        assertThat(subTask.getSubTaskTitle()).isEqualTo(title);
        assertThat(subTask.getSubTaskDescription()).isEqualTo(description);
        assertThat(subTask.getSubTaskStatus()).isEqualTo(status);
        assertThat(subTask.getSubTaskDeadline()).isEqualTo(deadline);
        assertThat(subTask.getSubTaskReminderDate()).isEqualTo(reminder);
    }

    @Test
    void shouldCreateEmptySubTaskWithNoArgsConstructor() {
        // When
        SubTask subTask = new SubTask();

        // Then
        assertThat(subTask).isNotNull();
    }
}
