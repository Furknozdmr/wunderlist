package com.furkanozdemir.adapter.todolist.rest;

import com.furkanozdemir.adapter.todolist.model.CreateSubTaskRequest;
import com.furkanozdemir.adapter.todolist.model.CreateTaskRequest;
import com.furkanozdemir.common.usecase.UseCaseHandler;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.usecase.model.*;
import com.furkanozdemir.todolist.usecase.model.response.TasksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/task")
public class TaskController {

    private final UseCaseHandler<TasksResponse, UserTaskUseCase> userTasksUseCaseHandler;

    private final UseCaseHandler<TasksResponse, ListTaskUseCase> listTasksUseCaseHandler;

    private final VoidUseCaseHandler<CreateTaskUseCase> createTaskUseCaseHandler;

    private final VoidUseCaseHandler<CreateSubTaskUseCase> createSubTaskUseCaseHandler;

    private final VoidUseCaseHandler<DeleteTaskUseCase> deleteTaskUseCaseHandler;

    private final VoidUseCaseHandler<DeleteSubTaskUseCase> deleteSubTaskUseCaseHandler;

    //private final VoidUseCaseHandler<AssignTaskUseCase> assignTaskUseCaseHandler;

    @PostMapping(path = "/create-task")
    public ResponseEntity<Void> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        createTaskUseCaseHandler.handle(
                new CreateTaskUseCase(createTaskRequest.getListId(), createTaskRequest.getUserId(), createTaskRequest.getTitle(),
                                      createTaskRequest.getDescription(), createTaskRequest.getDeadline(), createTaskRequest.getReminderDate()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "/create-sub-task")
    public ResponseEntity<Void> createSubTask(@RequestBody CreateSubTaskRequest createSubTaskRequest) {
        createSubTaskUseCaseHandler.handle(
                new CreateSubTaskUseCase(createSubTaskRequest.getTaskId(), createSubTaskRequest.getUserId(), createSubTaskRequest.getTitle(),
                                         createSubTaskRequest.getDescription(), createSubTaskRequest.getDeadline(),
                                         createSubTaskRequest.getReminderDate()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "/delete-task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String id) {
        deleteTaskUseCaseHandler.handle(new DeleteTaskUseCase(id));
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/task/{taskId}/delete-sub-task/{id}")
    public ResponseEntity<Void> deleteSubTask(@PathVariable("id") String id, @PathVariable("taskId") String taskId) {
        deleteSubTaskUseCaseHandler.handle(new DeleteSubTaskUseCase(id, taskId));
        return ResponseEntity.ok().build();
    }

    //    @PostMapping(path = "/assign-task")
    //    public ResponseEntity<Void> assignTask(@RequestBody AssignTodoListRequest assignTodoListRequest) {
    //        assignTodoListUseCaseHandler.handle(new AssignTodoListUseCase(assignTodoListRequest.getUserId(),assignTodoListRequest.getTodoListId()));
    //        return ResponseEntity.ok().build();
    //    } //TODO Yetişirse bak

    @GetMapping(path = "/{userId}/get-user-tasks")
    public ResponseEntity<TasksResponse> getUserTasks(@PathVariable("userId") String userId) {
        TasksResponse response = userTasksUseCaseHandler.handle(new UserTaskUseCase(userId));
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{listId}/get-list-tasks")
    public ResponseEntity<TasksResponse> getListTasks(@PathVariable("listId") String listId) {
        TasksResponse response = listTasksUseCaseHandler.handle(new ListTaskUseCase(listId));
        return ResponseEntity.ok(response);
    }
}
