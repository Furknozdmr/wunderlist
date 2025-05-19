package com.furkanozdemir.todolist.port;

import java.util.List;

public interface TodoListPort {
    List<TodoListDto> getAllTodoListsByUserId(String userId);

    void deleteTodoListById(String todoListId);

    void createTodoList(TodoListDto todoListDto);
}
