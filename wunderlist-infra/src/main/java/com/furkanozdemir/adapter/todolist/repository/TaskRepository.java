package com.furkanozdemir.adapter.todolist.repository;

import com.furkanozdemir.adapter.todolist.entity.Task;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends CouchbaseRepository<Task, String> {

    List<Task> findTasksByAssignUserUserId(String assignUserUserId);

    List<Task> findTasksByListId(String listId);

    List<Task> findTasksByReminderDateBefore(LocalDate reminderDateBefore);

}
