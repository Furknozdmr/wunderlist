package com.furkanozdemir.todolist.port;

import java.util.List;

public interface TaskPort {
    TaskDto findTaskById(String id);

    List<TaskDto> getAllTasksByUserId(String userId);

    List<TaskDto> getAllTasksByListId(String listId);

    void deleteTaskById(String id);

    void deleteSubTaskById(String id); //TODO Subtask id yollanmalı mı

    void createTask(TaskDto taskDto);

    void createSubTask(SubTaskDto subTaskDto);
}
