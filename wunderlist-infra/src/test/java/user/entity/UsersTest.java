package user.entity;

import com.furkanozdemir.adapter.user.entity.Users;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UsersTest {

    @Test
    void shouldCreateUserUsingBuilder() {
        OffsetDateTime now = OffsetDateTime.now();

        Users user = Users.builder()
                          .userId("1")
                          .email("furkn@hotmail.com")
                          .password("123")
                          .firstName("Furkan")
                          .lastName("Özdemir")
                          .createdAt(now)
                          .updatedAt(now)
                          .roles(List.of("USER", "ADMIN"))
                          .permissions(List.of("READ", "WRITE"))
                          .build();

        assertEquals("1", user.getUserId());
        assertEquals("furkn@hotmail.com", user.getEmail());
        assertEquals("123", user.getPassword());
        assertEquals("Furkan", user.getFirstName());
        assertEquals("Özdemir", user.getLastName());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
        assertEquals(List.of("USER", "ADMIN"), user.getRoles());
        assertEquals(List.of("READ", "WRITE"), user.getPermissions());
    }

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        Users user = new Users();
        OffsetDateTime now = OffsetDateTime.now();

        user.setUserId("2");
        user.setEmail("ozdmr@hotmail.com");
        user.setPassword("123456");
        user.setFirstName("Ahmet");
        user.setLastName("Yılmaz");
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setRoles(List.of("MODERATOR"));
        user.setPermissions(List.of("DELETE"));

        assertEquals("2", user.getUserId());
        assertEquals("ozdmr@hotmail.com", user.getEmail());
        assertEquals("123456", user.getPassword());
        assertEquals("Ahmet", user.getFirstName());
        assertEquals("Yılmaz", user.getLastName());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
        assertEquals(List.of("MODERATOR"), user.getRoles());
        assertEquals(List.of("DELETE"), user.getPermissions());
    }

    @Test
    void shouldUseAllArgsConstructorCorrectly() {
        OffsetDateTime now = OffsetDateTime.now();

        Users user = new Users("3", "admin@admin.com", "password", "Admin", "User", now, now, List.of("ADMIN"), List.of("ALL"));

        assertNotNull(user);
        assertEquals("3", user.getUserId());
        assertEquals("admin@admin.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("Admin", user.getFirstName());
        assertEquals("User", user.getLastName());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
        assertEquals(List.of("ADMIN"), user.getRoles());
        assertEquals(List.of("ALL"), user.getPermissions());
    }
}
