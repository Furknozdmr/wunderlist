package com.furkanozdemir.adapter.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignTodoListRequest {

    private String userEmail;

    private String todoListId;

}
