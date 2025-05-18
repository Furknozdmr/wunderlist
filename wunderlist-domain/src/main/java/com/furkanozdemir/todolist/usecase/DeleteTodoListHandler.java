package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.port.TodoListPort;
import com.furkanozdemir.todolist.usecase.model.CreateTodoListUseCase;
import com.furkanozdemir.todolist.usecase.model.DeleteTodoListUseCase;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class DeleteTodoListHandler implements VoidUseCaseHandler<DeleteTodoListUseCase> {

    private final TodoListPort todoListPort;

    @Override
    public void handle(DeleteTodoListUseCase useCase) {
        todoListPort.deleteTodoListById(useCase.id());

    }
}
