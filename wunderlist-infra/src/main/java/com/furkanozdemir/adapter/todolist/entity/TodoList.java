package com.furkanozdemir.adapter.todolist.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoList {

    @Id
    private Long id;

    private String title;

    private String description;

    private List<AssignUser> assignUsers;

    private Long createdBy;

    private LocalDateTime createdAt;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class AssignUser {

       private Long userId;

       private String firstName;

       private String lastName;
    }
}
