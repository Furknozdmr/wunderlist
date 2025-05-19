package com.furkanozdemir.adapter.todolist.entity;

import com.furkanozdemir.common.enums.TaskStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    private String id;

    private String listId;

    private String title;

    private String description;

    private AssignUser assignUser;

    private String createdBy;

    private LocalDateTime createdAt;

    private TaskStatus status;

    private LocalDate deadline;

    private LocalDate reminderDate;

    private List<SubTask> subTaskList;
}
