package com.furkanozdemir.adapter.user;

import com.furkanozdemir.adapter.user.entity.Users;
import com.furkanozdemir.adapter.user.repository.UserRepository;
import com.furkanozdemir.common.exception.UserNotFoundException;
import com.furkanozdemir.user.model.AssignUserDto;
import com.furkanozdemir.user.model.UserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailDataAdapter implements UserDetailPort {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDto> getUserDtoById(String userId) {
        Optional<Users> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            return Optional.of(new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<UserDto> getUserDtoByEmail(String email) {
        Optional<Users> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            return Optional.of(new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()));
        }
        return Optional.empty();
    }

    @Override
    public void createUser(String email, String firstName, String lastName, String password) {
        var encodedPassword = passwordEncoder.encode(password);
        Users user = Users.builder()
                .userId(UUID.randomUUID().toString())
                          .firstName(firstName).lastName(lastName).email(email).password(encodedPassword).build();
        userRepository.save(user);
    }

    @Override
    public AssignUserDto getAssignUserDtoById(String userId) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        return new AssignUserDto(users.getUserId(),users.getEmail(), users.getFirstName(), users.getLastName());
    }

    @Override
    public AssignUserDto getAssignUserDtoByEmail(String email) {
        Users users = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        return new AssignUserDto(users.getUserId(),users.getEmail(), users.getFirstName(), users.getLastName());
    }

}
