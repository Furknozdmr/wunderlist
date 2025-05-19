package com.furkanozdemir.todolist.usecase.model.response;

import com.furkanozdemir.todolist.port.TaskDto;

import java.util.List;

public record TasksResponse(List<TaskDto> taskCollection) {

}
