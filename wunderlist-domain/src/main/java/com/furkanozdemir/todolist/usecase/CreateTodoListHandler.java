package com.furkanozdemir.todolist.usecase;

import com.furkanozdemir.common.DomainComponent;
import com.furkanozdemir.common.exception.UserAlreadyExistException;
import com.furkanozdemir.common.exception.UserNotFoundException;
import com.furkanozdemir.common.usecase.VoidUseCaseHandler;
import com.furkanozdemir.common.usecase.model.NewUserUseCase;
import com.furkanozdemir.todolist.port.TodoListDto;
import com.furkanozdemir.todolist.port.TodoListPort;
import com.furkanozdemir.todolist.usecase.model.CreateTodoListUseCase;
import com.furkanozdemir.user.model.AssignUserDto;
import com.furkanozdemir.user.model.UserDto;
import com.furkanozdemir.user.port.UserDetailPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainComponent
@RequiredArgsConstructor
public class CreateTodoListHandler implements VoidUseCaseHandler<CreateTodoListUseCase> {

    private final TodoListPort todoListPort;

    private final UserDetailPort userDetailPort;

    @Override
    public void handle(CreateTodoListUseCase useCase) {
        UserDto userDto = userDetailPort.getUserDtoById(useCase.userId()).orElseThrow(UserNotFoundException::new);
        todoListPort.createTodoList(new TodoListDto(3L, useCase.title(), useCase.description(), List.of(new AssignUserDto(userDto.userId(),userDto.firstName(),userDto.lastName()))));

                //TODO couchbase id yaratan ne var allahtan başka
    }
}
