package com.furkanozdemir.adapter.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTodoListRequest {

    private Long userId;

    private String title;

    private String description;

}
