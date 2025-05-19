package com.furkanozdemir.todolist.usecase.model;



import java.time.LocalDate;


public record CreateTaskUseCase(String listId, String userId, String title,String description,LocalDate deadline, LocalDate reminderDate) {

}