package com.furkanozdemir.adapter.todolist.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignUser {

    private String userId;

    private String email;

    private String firstName;

    private String lastName;
}

