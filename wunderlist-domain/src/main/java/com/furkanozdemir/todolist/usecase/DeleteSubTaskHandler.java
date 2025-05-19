package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.model.DeleteSubTaskUseCase;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class DeleteSubTaskHandler implements VoidUseCaseHandler<DeleteSubTaskUseCase> {

    private final TaskPort taskPort;

    @Override
    public void handle(DeleteSubTaskUseCase useCase) {
        taskPort.deleteSubTaskById(useCase.id());

    }
}
