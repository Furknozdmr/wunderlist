package com.furkanozdemir.adapter.todolist.rest;

import com.furkanozdemir.adapter.todolist.model.AssignTodoListRequest;
import com.furkanozdemir.adapter.todolist.model.CreateTodoListRequest;
import com.furkanozdemir.common.usecase.UseCaseHandler;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.usecase.model.AssignTodoListUseCase;
import com.furkanozdemir.todolist.usecase.model.CreateTodoListUseCase;
import com.furkanozdemir.todolist.usecase.model.DeleteTodoListUseCase;
import com.furkanozdemir.todolist.usecase.model.TodoListUseCase;
import com.furkanozdemir.todolist.usecase.model.response.UserTodoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/list")
public class TodoListController {

    private final UseCaseHandler<UserTodoListResponse, TodoListUseCase> todoListUseCaseHandler;

    private final VoidUseCaseHandler<CreateTodoListUseCase> createTodoListUseCaseHandler;

    private final VoidUseCaseHandler<DeleteTodoListUseCase> deleteTodoListUseCaseHandler;

    private final VoidUseCaseHandler<AssignTodoListUseCase> assignTodoListUseCaseHandler;

    @PostMapping(path = "/create-todo-list")
    public ResponseEntity<Void> createTodoList(@RequestBody CreateTodoListRequest createTodoListRequest) {
        createTodoListUseCaseHandler.handle(new CreateTodoListUseCase(createTodoListRequest.getUserId(), createTodoListRequest.getTitle(),
                                                                      createTodoListRequest.getDescription()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "/delete-todo-list/{id}")
    public ResponseEntity<Void> deleteTodoList(@PathVariable("id") String id) {
        deleteTodoListUseCaseHandler.handle(new DeleteTodoListUseCase(id));
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/assign-todo-list")
    public ResponseEntity<Void> assignTodoList(@RequestBody AssignTodoListRequest assignTodoListRequest) {
        assignTodoListUseCaseHandler.handle(new AssignTodoListUseCase(assignTodoListRequest.getUserId(), assignTodoListRequest.getTodoListId()));
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{userId}/get-todo-lists")
    public ResponseEntity<UserTodoListResponse> getUserTodoLists(@PathVariable("userId") String userId) {
        UserTodoListResponse response = todoListUseCaseHandler.handle(new TodoListUseCase(userId));
        return ResponseEntity.ok(response);
    }
}
