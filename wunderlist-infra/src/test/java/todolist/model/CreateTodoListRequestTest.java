package todolist.model;

import com.furkanozdemir.adapter.todolist.model.CreateTodoListRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateTodoListRequestTest {

    @Test
    void shouldCreateCreateTodoListRequestUsingAllArgsConstructor() {
        String userId = "1";
        String title = "deneme";
        String description = "açıklama";

        CreateTodoListRequest request = new CreateTodoListRequest(userId, title, description);

        assertThat(request.getUserId()).isEqualTo(userId);
        assertThat(request.getTitle()).isEqualTo(title);
        assertThat(request.getDescription()).isEqualTo(description);
    }

    @Test
    void shouldSetFieldsUsingSetters() {
        CreateTodoListRequest request = new CreateTodoListRequest();

        String userId = "3";
        String title = "deneme";
        String description = "açıklama";

        request.setUserId(userId);
        request.setTitle(title);
        request.setDescription(description);

        assertThat(request.getUserId()).isEqualTo(userId);
        assertThat(request.getTitle()).isEqualTo(title);
        assertThat(request.getDescription()).isEqualTo(description);
    }

    @Test
    void shouldReturnNullFieldsWithNoArgsConstructor() {
        CreateTodoListRequest request = new CreateTodoListRequest();

        assertThat(request.getUserId()).isNull();
        assertThat(request.getTitle()).isNull();
        assertThat(request.getDescription()).isNull();
    }
}