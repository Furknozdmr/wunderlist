package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.exception.UserNotFoundException;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.todolist.port.TodoListDto;
import com.furkanozdemir.todolist.port.TodoListPort;
import com.furkanozdemir.todolist.usecase.model.CreateTodoListUseCase;
import com.furkanozdemir.user.model.AssignUserDto;
import com.furkanozdemir.user.model.UserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@DomainComponent
@RequiredArgsConstructor
public class CreateTodoListHandler implements VoidUseCaseHandler<CreateTodoListUseCase> {

    private final TodoListPort todoListPort;

    private final UserDetailPort userDetailPort;

    @Override
    public void handle(CreateTodoListUseCase useCase) {
        UserDto userDto = userDetailPort.getUserDtoById(useCase.userId()).orElseThrow(UserNotFoundException::new);
        todoListPort.createTodoList(new TodoListDto(UUID.randomUUID().toString(), useCase.title(), useCase.description(),new AssignUserDto(userDto.userId(), userDto.firstName(), userDto.lastName())));
    }
}
