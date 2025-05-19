package com.furkanozdemir.adapter.todolist.entity;

import com.furkanozdemir.common.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubTask{

    @Id
    private String subTaskId;

    private String subTaskTitle;

    private String subTaskDescription;

    private TaskStatus subTaskStatus;

    private LocalDate subTaskDeadline;

    private LocalDate subTaskReminderDate;
}