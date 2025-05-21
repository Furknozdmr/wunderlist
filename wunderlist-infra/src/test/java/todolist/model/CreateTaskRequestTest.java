package todolist.model;

import com.furkanozdemir.adapter.todolist.model.CreateTaskRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CreateTaskRequestTest {

    @Test
    void shouldCreateCreateTaskRequestUsingAllArgsConstructor() {
        String listId = "1";
        String userId = "2";
        String title = "deneme";
        String description = "açıklama";
        LocalDate deadline = LocalDate.of(2025, 6, 10);
        LocalDate reminderDate = LocalDate.of(2025, 6, 5);

        CreateTaskRequest request = new CreateTaskRequest(
                listId, userId, title, description, deadline, reminderDate
        );

        assertThat(request.getListId()).isEqualTo(listId);
        assertThat(request.getUserId()).isEqualTo(userId);
        assertThat(request.getTitle()).isEqualTo(title);
        assertThat(request.getDescription()).isEqualTo(description);
        assertThat(request.getDeadline()).isEqualTo(deadline);
        assertThat(request.getReminderDate()).isEqualTo(reminderDate);
    }

    @Test
    void shouldCreateCreateTaskRequestUsingSetters() {
        CreateTaskRequest request = new CreateTaskRequest();

        String listId = "3";
        String userId = "4";
        String title = "deneme";
        String description = "açıklama";
        LocalDate deadline = LocalDate.of(2025, 7, 1);
        LocalDate reminderDate = LocalDate.of(2025, 6, 25);

        request.setListId(listId);
        request.setUserId(userId);
        request.setTitle(title);
        request.setDescription(description);
        request.setDeadline(deadline);
        request.setReminderDate(reminderDate);

        assertThat(request.getListId()).isEqualTo(listId);
        assertThat(request.getUserId()).isEqualTo(userId);
        assertThat(request.getTitle()).isEqualTo(title);
        assertThat(request.getDescription()).isEqualTo(description);
        assertThat(request.getDeadline()).isEqualTo(deadline);
        assertThat(request.getReminderDate()).isEqualTo(reminderDate);
    }

    @Test
    void shouldReturnNullFieldsWithNoArgsConstructor() {
        CreateTaskRequest request = new CreateTaskRequest();

        assertThat(request.getListId()).isNull();
        assertThat(request.getUserId()).isNull();
        assertThat(request.getTitle()).isNull();
        assertThat(request.getDescription()).isNull();
        assertThat(request.getDeadline()).isNull();
        assertThat(request.getReminderDate()).isNull();
    }
}

