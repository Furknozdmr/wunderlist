package todolist.model;


import com.furkanozdemir.adapter.todolist.model.CreateSubTaskRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CreateSubTaskRequestTest {

    @Test
    void shouldCreateCreateSubTaskRequestUsingAllArgsConstructor() {
        // Given
        String taskId = "1";
        String userId = "1";
        String title = "deneme";
        String description = "açıklama";
        LocalDate deadline = LocalDate.of(2025, 5, 30);
        LocalDate reminderDate = LocalDate.of(2025, 5, 25);

        // When
        CreateSubTaskRequest request = new CreateSubTaskRequest(
                taskId, userId, title, description, deadline, reminderDate
        );

        // Then
        assertThat(request.getTaskId()).isEqualTo(taskId);
        assertThat(request.getUserId()).isEqualTo(userId);
        assertThat(request.getTitle()).isEqualTo(title);
        assertThat(request.getDescription()).isEqualTo(description);
        assertThat(request.getDeadline()).isEqualTo(deadline);
        assertThat(request.getReminderDate()).isEqualTo(reminderDate);
    }

    @Test
    void shouldCreateCreateSubTaskRequestUsingSetters() {
        // Given
        CreateSubTaskRequest request = new CreateSubTaskRequest();

        String taskId = "2";
        String userId = "02";
        String title = "deneme";
        String description = "açıklama";
        LocalDate deadline = LocalDate.of(2025, 6, 1);
        LocalDate reminderDate = LocalDate.of(2025, 5, 29);

        // When
        request.setTaskId(taskId);
        request.setUserId(userId);
        request.setTitle(title);
        request.setDescription(description);
        request.setDeadline(deadline);
        request.setReminderDate(reminderDate);

        // Then
        assertThat(request.getTaskId()).isEqualTo(taskId);
        assertThat(request.getUserId()).isEqualTo(userId);
        assertThat(request.getTitle()).isEqualTo(title);
        assertThat(request.getDescription()).isEqualTo(description);
        assertThat(request.getDeadline()).isEqualTo(deadline);
        assertThat(request.getReminderDate()).isEqualTo(reminderDate);
    }

    @Test
    void shouldReturnNullFieldsWithNoArgsConstructor() {
        // When
        CreateSubTaskRequest request = new CreateSubTaskRequest();

        // Then
        assertThat(request.getTaskId()).isNull();
        assertThat(request.getUserId()).isNull();
        assertThat(request.getTitle()).isNull();
        assertThat(request.getDescription()).isNull();
        assertThat(request.getDeadline()).isNull();
        assertThat(request.getReminderDate()).isNull();
    }
}