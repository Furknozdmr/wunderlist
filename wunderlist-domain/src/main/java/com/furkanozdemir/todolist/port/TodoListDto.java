package com.furkanozdemir.todolist.port;

import com.furkanozdemir.user.model.AssignUserDto;

import java.util.List;

public record TodoListDto(Long id, String title, String description, List<AssignUserDto> assignUsers) {

}
