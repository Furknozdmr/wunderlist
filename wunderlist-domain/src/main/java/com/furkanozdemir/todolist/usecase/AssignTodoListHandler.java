package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.port.TodoListPort;
import com.furkanozdemir.todolist.usecase.model.AssignTodoListUseCase;
import com.furkanozdemir.user.model.AssignUserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AssignTodoListHandler implements VoidUseCaseHandler<AssignTodoListUseCase> {

    private final TodoListPort todoListPort;

    private final UserDetailPort userDetailPort;

    @Override
    public void handle(AssignTodoListUseCase useCase) {
        AssignUserDto assignUserDto = userDetailPort.getAssignUserDtoByEmail(useCase.userEmail());
        todoListPort.assignTodoList(useCase.todoListId(), assignUserDto);
    }
}
