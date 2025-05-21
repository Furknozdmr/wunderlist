package todolist.model;

import com.furkanozdemir.adapter.todolist.model.AssignTaskRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssignTaskRequestTest {

    @Test
    void shouldCreateAssignTaskRequestUsingAllArgsConstructor() {
        String email = "furknozdmr@hotmail.com";
        String taskId = "2";

        AssignTaskRequest request = new AssignTaskRequest(email, taskId);

        assertThat(request.getUserEmail()).isEqualTo(email);
        assertThat(request.getTaskId()).isEqualTo(taskId);
    }

    @Test
    void shouldCreateAssignTaskRequestUsingNoArgsConstructorAndSetters() {
        AssignTaskRequest request = new AssignTaskRequest();

        String email = "aliveli@hotmail.com";
        String taskId = "6";

        request.setUserEmail(email);
        request.setTaskId(taskId);

        assertThat(request.getUserEmail()).isEqualTo(email);
        assertThat(request.getTaskId()).isEqualTo(taskId);
    }

    @Test
    void shouldReturnNullFieldsForEmptyConstructor() {
        AssignTaskRequest request = new AssignTaskRequest();

        assertThat(request.getUserEmail()).isNull();
        assertThat(request.getTaskId()).isNull();
    }
}
