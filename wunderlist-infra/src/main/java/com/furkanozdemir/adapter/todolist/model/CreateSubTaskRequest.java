package com.furkanozdemir.adapter.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubTaskRequest {

    private String taskId;

    private String userId;

    private String title;

    private String description;

    private LocalDate deadline;

    private LocalDate reminderDate;

}