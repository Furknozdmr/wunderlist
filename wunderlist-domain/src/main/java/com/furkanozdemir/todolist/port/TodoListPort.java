package com.furkanozdemir.todolist.port;

import java.util.List;

public interface TodoListPort {
    List<TodoListDto> getAllTodoListsByUserId(Long userId);

    void deleteTodoListById(Long todoListId);

    void createTodoList(TodoListDto todoListDto);
}
