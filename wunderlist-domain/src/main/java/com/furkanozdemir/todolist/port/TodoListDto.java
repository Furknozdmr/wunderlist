package com.furkanozdemir.todolist.port;

import com.furkanozdemir.user.model.AssignUserDto;

public record TodoListDto(String id, String title, String description, AssignUserDto assignUser) {

}
