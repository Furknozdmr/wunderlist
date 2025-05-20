package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.todolist.port.TodoListPort;
import com.furkanozdemir.todolist.usecase.model.AssignTaskUseCase;
import com.furkanozdemir.todolist.usecase.model.AssignTodoListUseCase;
import com.furkanozdemir.user.model.AssignUserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AssignTaskHandler implements VoidUseCaseHandler<AssignTaskUseCase> {

    private final TaskPort taskPort;

    private final UserDetailPort userDetailPort;

    @Override
    public void handle(AssignTaskUseCase useCase) {
        AssignUserDto assignUserDto = userDetailPort.getAssignUserDtoByEmail(useCase.userEmail());
        taskPort.assignTask(useCase.taskId(), assignUserDto);
    }
}
