package com.furkanozdemir.adapter.todolist.model;

import com.furkanozdemir.common.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeTaskStatusRequest {

    private TaskStatus newStatus;

}