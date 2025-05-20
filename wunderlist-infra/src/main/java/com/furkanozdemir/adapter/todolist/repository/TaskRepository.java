package com.furkanozdemir.adapter.todolist.repository;

import com.furkanozdemir.adapter.todolist.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task, String> {

    List<Task> findTasksByAssignUserUserId(String assignUserUserId);

    List<Task> findTasksByListId(String listId);

    List<Task> findTasksByReminderDateBefore(LocalDate reminderDateBefore);

}
