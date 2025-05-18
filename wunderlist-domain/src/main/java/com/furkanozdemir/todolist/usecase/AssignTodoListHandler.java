package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.usecase.model.AssignTodoListUseCase;
import com.furkanozdemir.todolist.usecase.model.CreateTodoListUseCase;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AssignTodoListHandler implements VoidUseCaseHandler<AssignTodoListUseCase> {

    @Override
    public void handle(AssignTodoListUseCase useCase) {

    }
}
