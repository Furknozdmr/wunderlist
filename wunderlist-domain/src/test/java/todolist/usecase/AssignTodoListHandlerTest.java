package todolist.usecase;

import com.furkanozdemir.todolist.port.TodoListPort;
import com.furkanozdemir.todolist.usecase.AssignTodoListHandler;
import com.furkanozdemir.todolist.usecase.model.AssignTodoListUseCase;
import com.furkanozdemir.user.model.AssignUserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AssignTodoListHandlerTest {

    @Mock
    private TodoListPort todoListPort;

    @Mock
    private UserDetailPort userDetailPort;

    @InjectMocks
    private AssignTodoListHandler assignTodoListHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handle_shouldAssignTodoListToUser() {
        String todoListId = "list1";
        String userEmail = "furkn@hotmail.com";

        AssignTodoListUseCase useCase = new AssignTodoListUseCase(userEmail, todoListId);

        AssignUserDto assignUserDto = new AssignUserDto("user1", userEmail, "Furkan", "Özdemir");

        when(userDetailPort.getAssignUserDtoByEmail(userEmail)).thenReturn(assignUserDto);

        assignTodoListHandler.handle(useCase);

        verify(userDetailPort, times(1)).getAssignUserDtoByEmail(userEmail);
        verify(todoListPort, times(1)).assignTodoList(todoListId, assignUserDto);
    }
}

