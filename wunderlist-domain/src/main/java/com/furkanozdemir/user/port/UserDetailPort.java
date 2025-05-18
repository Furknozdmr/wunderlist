package com.furkanozdemir.user.port;

import com.furkanozdemir.user.model.UserDto;

import java.util.Optional;

public interface UserDetailPort {
    Optional<UserDto> getUserDtoById(Long userId);

    Optional<UserDto> getUserDtoByEmail(String email);

    void createUser(String email, String firstName, String lastName, String password);
}
