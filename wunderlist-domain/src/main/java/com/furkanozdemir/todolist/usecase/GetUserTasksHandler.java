package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.UseCaseHandler;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.port.TaskDto;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.usecase.model.DeleteTaskUseCase;
import com.furkanozdemir.todolist.usecase.model.UserTaskUseCase;
import com.furkanozdemir.todolist.usecase.model.response.TasksResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainComponent
@RequiredArgsConstructor
public class GetUserTasksHandler implements UseCaseHandler<TasksResponse, UserTaskUseCase> {

    private final TaskPort taskPort;

    @Override
    public TasksResponse handle(UserTaskUseCase useCase) {
        List<TaskDto> allTasksByUserId = taskPort.getAllTasksByUserId(useCase.userId());
        return new TasksResponse(allTasksByUserId);
    }
}
