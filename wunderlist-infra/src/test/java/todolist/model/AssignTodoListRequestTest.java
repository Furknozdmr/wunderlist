package todolist.model;

import com.furkanozdemir.adapter.todolist.model.AssignTodoListRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssignTodoListRequestTest {

    @Test
    void shouldCreateAssignTodoListRequestUsingAllArgsConstructor() {
        String email = "furknozdmr@hotmail.com";
        String todoListId = "1";

        AssignTodoListRequest request = new AssignTodoListRequest(email, todoListId);

        assertThat(request.getUserEmail()).isEqualTo(email);
        assertThat(request.getTodoListId()).isEqualTo(todoListId);
    }

    @Test
    void shouldCreateAssignTodoListRequestUsingNoArgsConstructorAndSetters() {
        AssignTodoListRequest request = new AssignTodoListRequest();

        String email = "mehmet@hotmail.com";
        String todoListId = "2";

        request.setUserEmail(email);
        request.setTodoListId(todoListId);

        assertThat(request.getUserEmail()).isEqualTo(email);
        assertThat(request.getTodoListId()).isEqualTo(todoListId);
    }

    @Test
    void shouldReturnNullFieldsForEmptyConstructor() {
        AssignTodoListRequest request = new AssignTodoListRequest();

        assertThat(request.getUserEmail()).isNull();
        assertThat(request.getTodoListId()).isNull();
    }
}
