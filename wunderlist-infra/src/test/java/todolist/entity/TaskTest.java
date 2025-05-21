package todolist.entity;

import com.furkanozdemir.adapter.todolist.entity.AssignUser;
import com.furkanozdemir.adapter.todolist.entity.SubTask;
import com.furkanozdemir.adapter.todolist.entity.Task;
import com.furkanozdemir.common.enums.TaskStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {

    @Test
    void shouldCreateTaskWithBuilder() {
        String id = "12";
        String listId = "123";
        String title = "deneme";
        String description = "açıklama";
        AssignUser assignUser = new AssignUser("1", "furknozdmr@hotmail.com", "furkan","özdemir");
        String createdBy = "furkanozdemir";
        LocalDateTime createdAt = LocalDateTime.now();
        TaskStatus status = TaskStatus.IN_PROGRESS;
        LocalDate deadline = LocalDate.of(2025, 6, 30);
        LocalDate reminder = LocalDate.of(2025, 6, 25);
        SubTask subTask = new SubTask("s1", "Deneme", "Açıklama", TaskStatus.IN_PROGRESS, LocalDate.of(2025, 6, 28), LocalDate.of(2025, 6, 26));
        List<SubTask> subTasks = List.of(subTask);

        Task task = Task.builder()
                        .id(id)
                        .listId(listId)
                        .title(title)
                        .description(description)
                        .assignUser(assignUser)
                        .createdBy(createdBy)
                        .createdAt(createdAt)
                        .status(status)
                        .deadline(deadline)
                        .reminderDate(reminder)
                        .subTaskList(subTasks)
                        .build();

        assertThat(task.getId()).isEqualTo(id);
        assertThat(task.getListId()).isEqualTo(listId);
        assertThat(task.getTitle()).isEqualTo(title);
        assertThat(task.getDescription()).isEqualTo(description);
        assertThat(task.getAssignUser()).isEqualTo(assignUser);
        assertThat(task.getCreatedBy()).isEqualTo(createdBy);
        assertThat(task.getCreatedAt()).isEqualTo(createdAt);
        assertThat(task.getStatus()).isEqualTo(status);
        assertThat(task.getDeadline()).isEqualTo(deadline);
        assertThat(task.getReminderDate()).isEqualTo(reminder);
        assertThat(task.getSubTaskList()).containsExactly(subTask);
    }

    @Test
    void shouldSetAndGetFieldsManually() {
        Task task = new Task();
        String id = "211";
        String listId = "323";
        String title = "Deneme";
        String description = "Açıklama";
        AssignUser assignUser = new AssignUser("1", "aliveli@hotmail.com", "Ali","Veli");
        String createdBy = "tester";
        LocalDateTime createdAt = LocalDateTime.of(2025, 5, 20, 12, 30);
        TaskStatus status = TaskStatus.COMPLETED;
        LocalDate deadline = LocalDate.of(2025, 6, 1);
        LocalDate reminder = LocalDate.of(2025, 5, 30);
        SubTask subTask = new SubTask("2", "Deneme", "Açıklama", TaskStatus.COMPLETED, deadline, reminder);
        List<SubTask> subTasks = List.of(subTask);

        task.setId(id);
        task.setListId(listId);
        task.setTitle(title);
        task.setDescription(description);
        task.setAssignUser(assignUser);
        task.setCreatedBy(createdBy);
        task.setCreatedAt(createdAt);
        task.setStatus(status);
        task.setDeadline(deadline);
        task.setReminderDate(reminder);
        task.setSubTaskList(subTasks);

        assertThat(task.getId()).isEqualTo(id);
        assertThat(task.getListId()).isEqualTo(listId);
        assertThat(task.getTitle()).isEqualTo(title);
        assertThat(task.getDescription()).isEqualTo(description);
        assertThat(task.getAssignUser()).isEqualTo(assignUser);
        assertThat(task.getCreatedBy()).isEqualTo(createdBy);
        assertThat(task.getCreatedAt()).isEqualTo(createdAt);
        assertThat(task.getStatus()).isEqualTo(status);
        assertThat(task.getDeadline()).isEqualTo(deadline);
        assertThat(task.getReminderDate()).isEqualTo(reminder);
        assertThat(task.getSubTaskList()).containsExactly(subTask);
    }

    @Test
    void shouldCreateEmptyTaskWithNoArgsConstructor() {
        // When
        Task task = new Task();

        // Then
        assertThat(task).isNotNull();
    }
}

