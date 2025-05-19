package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.UseCaseHandler;
import com.furkanozdemir.todolist.port.TodoListPort;
import com.furkanozdemir.todolist.usecase.model.TodoListUseCase;
import com.furkanozdemir.todolist.usecase.model.response.UserTodoListResponse;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class GetUserTodoListsHandler implements UseCaseHandler<UserTodoListResponse, TodoListUseCase> {

    private final TodoListPort todoListPort;

    @Override
    public UserTodoListResponse handle(TodoListUseCase useCase) {
        return new UserTodoListResponse(todoListPort.getAllTodoListsByUserId(useCase.userId()));
    }
}
