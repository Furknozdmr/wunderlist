package com.furkanozdemir.todolist.port;

import com.furkanozdemir.common.enums.TaskStatus;
import com.furkanozdemir.user.model.AssignUserDto;

import java.util.List;

public interface TaskPort {

    TaskDto findTaskById(String id);

    List<TaskDto> getAllTasksByUserId(String userId);

    List<TaskDto> getAllTasksByListId(String listId);

    void deleteTaskById(String id);

    void deleteSubTaskById(String subTaskId, String taskId);

    void createTask(TaskDto taskDto);

    void createSubTask(SubTaskDto subTaskDto, String taskId);

    void changeTaskStatus(String taskId, TaskStatus status);

    void assignTask(String taskId, AssignUserDto assignUserDto);
}
