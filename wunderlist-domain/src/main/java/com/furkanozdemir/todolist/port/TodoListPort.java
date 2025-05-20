package com.furkanozdemir.todolist.port;

import com.furkanozdemir.user.model.AssignUserDto;

import java.util.List;

public interface TodoListPort {

    List<TodoListDto> getAllTodoListsByUserId(String userId);

    void deleteTodoListById(String todoListId);

    void createTodoList(TodoListDto todoListDto);

    void assignTodoList(String todoListId, AssignUserDto assignUserDto);
}
