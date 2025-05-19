package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.enums.TaskStatus;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.port.TaskDto;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.model.CreateTaskUseCase;
import com.furkanozdemir.user.model.AssignUserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@DomainComponent
@RequiredArgsConstructor
public class CreateTaskHandler implements VoidUseCaseHandler<CreateTaskUseCase> {

    private final TaskPort taskPort;

    private final UserDetailPort userDetailPort;

    @Override
    public void handle(CreateTaskUseCase useCase) {
        AssignUserDto assignUserDtoById = userDetailPort.getAssignUserDtoById(useCase.userId());
        taskPort.createTask(new TaskDto(UUID.randomUUID().toString(), useCase.title(), useCase.description(), assignUserDtoById, useCase.userId(),
                                        LocalDateTime.now(), TaskStatus.PENDING, useCase.deadline(), useCase.reminderDate(),
                                        Collections.emptyList()));
    }
}
