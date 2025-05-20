package todolist.entity;

import com.furkanozdemir.adapter.todolist.entity.AssignUser;
import com.furkanozdemir.adapter.todolist.entity.TodoList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TodoListTest {

    @Test
    void shouldCreateTodoListUsingBuilder() {
        // Given
        String id = "1";
        String title = "deneme";
        String description = "açıklama";
        AssignUser assignUser = new AssignUser("1", "furknozdmr@hotmail.com", "Furkan","Özdemir");
        String createdBy = "admin";
        LocalDateTime createdAt = LocalDateTime.of(2025, 5, 20, 10, 30);

        // When
        TodoList todoList = TodoList.builder()
                                    .id(id)
                                    .title(title)
                                    .description(description)
                                    .assignUser(assignUser)
                                    .createdBy(createdBy)
                                    .createdAt(createdAt)
                                    .build();

        // Then
        assertThat(todoList.getId()).isEqualTo(id);
        assertThat(todoList.getTitle()).isEqualTo(title);
        assertThat(todoList.getDescription()).isEqualTo(description);
        assertThat(todoList.getAssignUser()).isEqualTo(assignUser);
        assertThat(todoList.getCreatedBy()).isEqualTo(createdBy);
        assertThat(todoList.getCreatedAt()).isEqualTo(createdAt);
    }

    @Test
    void shouldSetAndGetFieldsManually() {
        // Given
        TodoList todoList = new TodoList();

        String id = "19";
        String title = "deneme2";
        String description = "açıklama2";
        AssignUser assignUser = new AssignUser("3", "aliveli2@hotmail.com", "Ali","Veli");
        String createdBy = "Ali";
        LocalDateTime createdAt = LocalDateTime.of(2025, 5, 21, 14, 0);

        // When
        todoList.setId(id);
        todoList.setTitle(title);
        todoList.setDescription(description);
        todoList.setAssignUser(assignUser);
        todoList.setCreatedBy(createdBy);
        todoList.setCreatedAt(createdAt);

        // Then
        assertThat(todoList.getId()).isEqualTo(id);
        assertThat(todoList.getTitle()).isEqualTo(title);
        assertThat(todoList.getDescription()).isEqualTo(description);
        assertThat(todoList.getAssignUser()).isEqualTo(assignUser);
        assertThat(todoList.getCreatedBy()).isEqualTo(createdBy);
        assertThat(todoList.getCreatedAt()).isEqualTo(createdAt);
    }

    @Test
    void shouldCreateEmptyTodoListWithNoArgsConstructor() {
        // When
        TodoList todoList = new TodoList();

        // Then
        assertThat(todoList).isNotNull();
    }
}