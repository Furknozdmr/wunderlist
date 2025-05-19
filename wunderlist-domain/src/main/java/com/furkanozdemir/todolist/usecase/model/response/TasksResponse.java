package com.furkanozdemir.todolist.usecase.model.response;


import com.furkanozdemir.todolist.port.TaskDto;
import com.furkanozdemir.todolist.port.TodoListDto;

import java.util.List;

public record TasksResponse(List<TaskDto> taskCollection) {
}
