package com.furkanozdemir.todolist.port;

import com.furkanozdemir.common.enums.TaskStatus;
import com.furkanozdemir.user.model.AssignUserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record TaskDto(String id, String title, String description,AssignUserDto assignUser, String createdBy,
                      LocalDateTime createdAt, TaskStatus status, LocalDate deadline, LocalDate reminderDate,List<SubTaskDto> subTaskList) {
}
