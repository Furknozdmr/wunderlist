package com.furkanozdemir.todolist.usecase.model;

import com.furkanozdemir.common.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record ChangeTaskStatusUseCase(String taskId, TaskStatus newStatus) {

}