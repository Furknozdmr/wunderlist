package todolist.model;

import com.furkanozdemir.adapter.todolist.model.AssignTaskRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssignTaskRequestTest {

    @Test
    void shouldCreateAssignTaskRequestUsingAllArgsConstructor() {
        // Given
        String email = "furknozdmr@hotmail.com";
        String taskId = "2";

        // When
        AssignTaskRequest request = new AssignTaskRequest(email, taskId);

        // Then
        assertThat(request.getUserEmail()).isEqualTo(email);
        assertThat(request.getTaskId()).isEqualTo(taskId);
    }

    @Test
    void shouldCreateAssignTaskRequestUsingNoArgsConstructorAndSetters() {
        // Given
        AssignTaskRequest request = new AssignTaskRequest();

        String email = "aliveli@hotmail.com";
        String taskId = "6";

        // When
        request.setUserEmail(email);
        request.setTaskId(taskId);

        // Then
        assertThat(request.getUserEmail()).isEqualTo(email);
        assertThat(request.getTaskId()).isEqualTo(taskId);
    }

    @Test
    void shouldReturnNullFieldsForEmptyConstructor() {
        // When
        AssignTaskRequest request = new AssignTaskRequest();

        // Then
        assertThat(request.getUserEmail()).isNull();
        assertThat(request.getTaskId()).isNull();
    }
}
