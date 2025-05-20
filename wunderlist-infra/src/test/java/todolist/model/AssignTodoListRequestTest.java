package todolist.model;

import com.furkanozdemir.adapter.todolist.model.AssignTodoListRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssignTodoListRequestTest {

    @Test
    void shouldCreateAssignTodoListRequestUsingAllArgsConstructor() {
        // Given
        String email = "furknozdmr@hotmail.com";
        String todoListId = "1";

        // When
        AssignTodoListRequest request = new AssignTodoListRequest(email, todoListId);

        // Then
        assertThat(request.getUserEmail()).isEqualTo(email);
        assertThat(request.getTodoListId()).isEqualTo(todoListId);
    }

    @Test
    void shouldCreateAssignTodoListRequestUsingNoArgsConstructorAndSetters() {
        // Given
        AssignTodoListRequest request = new AssignTodoListRequest();

        String email = "mehmet@hotmail.com";
        String todoListId = "2";

        // When
        request.setUserEmail(email);
        request.setTodoListId(todoListId);

        // Then
        assertThat(request.getUserEmail()).isEqualTo(email);
        assertThat(request.getTodoListId()).isEqualTo(todoListId);
    }

    @Test
    void shouldReturnNullFieldsForEmptyConstructor() {
        // When
        AssignTodoListRequest request = new AssignTodoListRequest();

        // Then
        assertThat(request.getUserEmail()).isNull();
        assertThat(request.getTodoListId()).isNull();
    }
}
