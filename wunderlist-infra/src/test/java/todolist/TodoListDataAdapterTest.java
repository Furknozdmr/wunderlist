package todolist;

import com.furkanozdemir.adapter.todolist.TodoListDataAdapter;
import com.furkanozdemir.adapter.todolist.entity.TodoList;
import com.furkanozdemir.adapter.todolist.repository.TodoListRepository;
import com.furkanozdemir.common.exception.TodoListNotFoundException;
import com.furkanozdemir.common.mapper.TodoListMapper;
import com.furkanozdemir.todolist.port.TodoListDto;
import com.furkanozdemir.user.model.AssignUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class TodoListDataAdapterTest {

    @Mock
    private TodoListRepository todoListRepository;

    @Mock
    private TodoListMapper todoListMapper;

    @InjectMocks
    private TodoListDataAdapter todoListDataAdapter;

    private TodoListDto sampleDto;

    private TodoList sampleEntity;

    @BeforeEach
    void setUp() {
        AssignUserDto assignUserDto = new AssignUserDto("122", "furknozdmr@hotmail.com", "Furkan", "Özdemir");

        sampleDto = new TodoListDto("1", "deneme", "açıklama", assignUserDto);

        sampleEntity = new TodoList();
        sampleEntity.setId("1");
        sampleEntity.setTitle("deneme");
    }

    @Test
    void shouldGetAllTodoListsByUserId() {
        List<TodoList> entities = List.of(sampleEntity);
        List<TodoListDto> dtos = List.of(sampleDto);

        when(todoListRepository.findTodoListsByAssignUserUserId("1234")).thenReturn(entities);
        when(todoListMapper.toDtoList(entities)).thenReturn(dtos);

        List<TodoListDto> result = todoListDataAdapter.getAllTodoListsByUserId("1234");

        assertEquals(1, result.size());
        assertEquals("deneme", result.get(0).title());
        verify(todoListRepository).findTodoListsByAssignUserUserId("1234");
        verify(todoListMapper).toDtoList(entities);
    }

    @Test
    void shouldDeleteTodoListById() {
        todoListDataAdapter.deleteTodoListById("1");
        verify(todoListRepository).deleteById("1");
    }

    @Test
    void shouldCreateTodoList() {
        when(todoListMapper.toEntity(sampleDto)).thenReturn(sampleEntity);

        todoListDataAdapter.createTodoList(sampleDto);

        verify(todoListRepository).save(sampleEntity);
    }

    @Test
    void shouldAssignTodoListSuccessfully() {
        AssignUserDto assignUserDto = new AssignUserDto("1", "furkn@hotmail.com", "Furkan", "Özdemir");

        when(todoListRepository.findById("1")).thenReturn(Optional.of(sampleEntity));
        when(todoListMapper.toEntity(assignUserDto)).thenReturn(
                new com.furkanozdemir.adapter.todolist.entity.AssignUser("1", "furkn@hotmail.com", "Furkan", "Özdemir"));

        todoListDataAdapter.assignTodoList("1", assignUserDto);

        assertNotNull(sampleEntity.getAssignUser());
        verify(todoListRepository).save(sampleEntity);
    }

    @Test
    void shouldThrowExceptionWhenAssigningToNonexistentList() {
        AssignUserDto assignUserDto = new AssignUserDto("1", "furkn@hotmail.com", "Furkan", "Özdemir");

        when(todoListRepository.findById("invalidId")).thenReturn(Optional.empty());

        assertThrows(TodoListNotFoundException.class, () -> todoListDataAdapter.assignTodoList("invalidId", assignUserDto));

        verify(todoListRepository, never()).save(any());
    }
}
