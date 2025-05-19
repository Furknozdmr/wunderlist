package com.furkanozdemir.adapter.todolist.repository;

import com.furkanozdemir.adapter.todolist.entity.TodoList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoListRepository extends CrudRepository<TodoList, String> {

    List<TodoList> findTodoListsByAssignUserUserId(String assignUserUserId);
}
