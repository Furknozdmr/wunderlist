package com.furkanozdemir.adapter.todolist.repository;

import com.furkanozdemir.adapter.todolist.entity.TodoList;
import com.furkanozdemir.adapter.user.entity.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TodoListRepository extends CrudRepository<TodoList, Long> {

    List<TodoList> findTodoListsByAssignUserIdsIn(List<Long> assignUserIds);
}
