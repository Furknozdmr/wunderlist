package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.model.ChangeTaskStatusUseCase;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class ChangeTaskStatusHandler implements VoidUseCaseHandler<ChangeTaskStatusUseCase> {

    private final TaskPort taskPort;

    @Override
    public void handle(ChangeTaskStatusUseCase useCase) {
        taskPort.changeTaskStatus(useCase.taskId(), useCase.newStatus());
    }
}
