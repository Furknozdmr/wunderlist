package user;

import com.furkanozdemir.adapter.user.UserDetailDataAdapter;
import com.furkanozdemir.adapter.user.entity.Users;
import com.furkanozdemir.adapter.user.repository.UserRepository;
import com.furkanozdemir.common.exception.UserNotFoundException;
import com.furkanozdemir.user.model.AssignUserDto;
import com.furkanozdemir.user.model.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserDetailDataAdapterTest {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private UserDetailDataAdapter adapter;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        adapter = new UserDetailDataAdapter(userRepository, passwordEncoder);
    }

    @Test
    void shouldReturnUserDtoWhenUserExistsById() {
        Users user = sampleUser();
        when(userRepository.findById("user-1")).thenReturn(Optional.of(user));

        Optional<UserDto> result = adapter.getUserDtoById("user-1");

        assertThat(result).isPresent();
        assertThat(result.get().email()).isEqualTo("deneme@hotmail.com");
    }

    @Test
    void shouldReturnEmptyWhenUserNotFoundById() {
        when(userRepository.findById("user-99")).thenReturn(Optional.empty());
        assertThat(adapter.getUserDtoById("user-99")).isEmpty();
    }

    @Test
    void shouldReturnUserDtoWhenUserExistsByEmail() {
        Users user = sampleUser();
        when(userRepository.findByEmail("furknozdmr@hotmail.com")).thenReturn(Optional.of(user));

        Optional<UserDto> result = adapter.getUserDtoByEmail("furknozdmr@hotmail.com");

        assertThat(result).isPresent();
        assertThat(result.get().firstName()).isEqualTo("Furkan");
    }

    @Test
    void shouldReturnEmptyWhenUserNotFoundByEmail() {
        when(userRepository.findByEmail("furkn@hotmail.com")).thenReturn(Optional.empty());
        assertThat(adapter.getUserDtoByEmail("furkn@hotmail.com")).isEmpty();
    }

    @Test
    void shouldCreateUserAndEncodePassword() {
        String rawPassword = "password123";
        String encodedPassword = "encoded-pass";
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        adapter.createUser("ozdmr@hotmail.com", "Ahmet", "Yılmaz", rawPassword);

        ArgumentCaptor<Users> captor = ArgumentCaptor.forClass(Users.class);
        verify(userRepository).save(captor.capture());
        Users savedUser = captor.getValue();

        assertThat(savedUser.getEmail()).isEqualTo("ozdmr@hotmail.com");
        assertThat(savedUser.getFirstName()).isEqualTo("Ahmet");
        assertThat(savedUser.getPassword()).isEqualTo(encodedPassword);
    }

    @Test
    void shouldReturnAssignUserDtoByIdIfExists() {
        Users user = sampleUser();
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        AssignUserDto dto = adapter.getAssignUserDtoById("1");

        assertThat(dto.userId()).isEqualTo("1");
        assertThat(dto.firstName()).isEqualTo("Furkan");
    }

    @Test
    void shouldThrowWhenUserNotFoundByIdForAssignUserDto() {
        when(userRepository.findById("invalid-id")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adapter.getAssignUserDtoById("invalid-id")).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void shouldReturnAssignUserDtoByEmailIfExists() {
        Users user = sampleUser();
        when(userRepository.findByEmail("deneme@hotmail.com")).thenReturn(Optional.of(user));

        AssignUserDto dto = adapter.getAssignUserDtoByEmail("deneme@hotmail.com");

        assertThat(dto.email()).isEqualTo("deneme@hotmail.com");
        assertThat(dto.lastName()).isEqualTo("Özdemir");
    }

    @Test
    void shouldThrowWhenUserNotFoundByEmailForAssignUserDto() {
        when(userRepository.findByEmail("deneme2@hotmail.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adapter.getAssignUserDtoByEmail("deneme2@hotmail.com")).isInstanceOf(UserNotFoundException.class);
    }

    private Users sampleUser() {
        return Users.builder().userId("1").email("deneme@hotmail.com").password("encoded-password").firstName("Furkan").lastName("Özdemir").build();
    }
}
