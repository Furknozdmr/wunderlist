package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.enums.TaskStatus;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.port.SubTaskDto;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.model.CreateSubTaskUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@DomainComponent
@RequiredArgsConstructor
public class CreateSubTaskHandler implements VoidUseCaseHandler<CreateSubTaskUseCase> {

    private final TaskPort taskPort;

    @Override
    public void handle(CreateSubTaskUseCase useCase) {
        taskPort.createSubTask(
                new SubTaskDto(UUID.randomUUID().toString(), useCase.title(), useCase.description(), TaskStatus.PENDING, useCase.deadline(),
                               useCase.reminderDate()), useCase.taskId());
    }
}
