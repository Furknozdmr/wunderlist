package com.furkanozdemir.todolist.port;


import com.furkanozdemir.common.enums.TaskStatus;
import java.time.LocalDate;

public record SubTaskDto(String subTaskId, String subTaskTitle, String subTaskDescription, TaskStatus subTaskStatus,
                         LocalDate subTaskDeadline, LocalDate subTaskReminderDate) {
}
