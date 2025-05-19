package com.furkanozdemir.todolist.usecase.model;


import java.time.LocalDate;

public record CreateSubTaskUseCase(String taskId, String userId, String title,String description,LocalDate deadline, LocalDate reminderDate) {
}