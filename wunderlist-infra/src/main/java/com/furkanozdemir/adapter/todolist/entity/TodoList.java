package com.furkanozdemir.adapter.todolist.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoList {

    @Id
    private String id;

    private String title;

    private String description;

    private AssignUser assignUser;

    private String createdBy;

    private LocalDateTime createdAt;
}
