package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.model.DeleteTaskUseCase;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class DeleteTaskHandler implements VoidUseCaseHandler<DeleteTaskUseCase> {

    private final TaskPort taskPort;

    @Override
    public void handle(DeleteTaskUseCase useCase) {
        taskPort.deleteTaskById(useCase.id());

    }
}
