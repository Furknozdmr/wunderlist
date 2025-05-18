package com.furkanozdemir.user.model;

public record UserDto(Long userId, String firstName, String lastName, String email, String password) {
}
