package todolist.entity;

import com.furkanozdemir.adapter.todolist.entity.AssignUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AssignUserTest {

    @Test
    void shouldCreateAssignUserUsingAllArgsConstructor() {
        AssignUser user = new AssignUser("1", "furknozdmr@hotmail.com", "Furkan", "Özdemir");

        assertEquals("1", user.getUserId());
        assertEquals("furknozdmr@hotmail.com", user.getEmail());
        assertEquals("Furkan", user.getFirstName());
        assertEquals("Özdemir", user.getLastName());
    }

    @Test
    void shouldCreateAssignUserUsingNoArgsConstructorAndSetters() {
        AssignUser user = new AssignUser();
        user.setUserId("2");
        user.setEmail("aliveli@hotmail.com");
        user.setFirstName("Ali");
        user.setLastName("Veli");

        assertEquals("2", user.getUserId());
        assertEquals("aliveli@hotmail.com", user.getEmail());
        assertEquals("Ali", user.getFirstName());
        assertEquals("Veli", user.getLastName());
    }

    @Test
    void shouldCreateAssignUserUsingBuilder() {
        AssignUser user = AssignUser.builder()
                                    .userId("3")
                                    .email("alimetin@hotmail.com")
                                    .firstName("Ali")
                                    .lastName("Metin")
                                    .build();

        assertEquals("3", user.getUserId());
        assertEquals("alimetin@hotmail.com", user.getEmail());
        assertEquals("Ali", user.getFirstName());
        assertEquals("Metin", user.getLastName());
    }

}
