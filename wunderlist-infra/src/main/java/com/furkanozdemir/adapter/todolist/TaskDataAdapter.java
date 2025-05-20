package com.furkanozdemir.adapter.todolist;

import com.furkanozdemir.adapter.todolist.entity.Task;
import com.furkanozdemir.adapter.todolist.repository.TaskRepository;
import com.furkanozdemir.common.enums.TaskStatus;
import com.furkanozdemir.common.exception.TaskNotFoundException;
import com.furkanozdemir.common.mapper.TaskMapper;
import com.furkanozdemir.todolist.port.SubTaskDto;
import com.furkanozdemir.todolist.port.TaskDto;
import com.furkanozdemir.todolist.port.TaskPort;
import com.furkanozdemir.user.model.AssignUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskDataAdapter implements TaskPort {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Override
    public TaskDto findTaskById(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskDto> getAllTasksByUserId(String userId) {
        List<Task> tasksByAssignUserUserId = taskRepository.findTasksByAssignUserUserId(userId);
        return taskMapper.toDtoList(tasksByAssignUserUserId);
    }

    @Override
    public List<TaskDto> getAllTasksByListId(String listId) {
        List<Task> tasksByListId = taskRepository.findTasksByListId(listId);
        return taskMapper.toDtoList(tasksByListId);
    }

    @Override
    public void deleteTaskById(String id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteSubTaskById(String subTaskId, String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        if (isNull(task.getSubTaskList()) || task.getSubTaskList().isEmpty()) {
            return;
        }
        task.getSubTaskList().removeIf(subTask -> subTask.getSubTaskId().equals(subTaskId));
        taskRepository.save(task);
    }

    @Override
    public void createTask(TaskDto taskDto) {
        taskRepository.save(taskMapper.toEntity(taskDto));
    }

    @Override
    public void createSubTask(SubTaskDto subTaskDto, String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        if (isNull(task.getSubTaskList())) {
            task.setSubTaskList(new ArrayList<>());
        }
        var subTask = taskMapper.toEntity(subTaskDto);
        task.getSubTaskList().add(subTask);
        taskRepository.save(task);
    }

    @Override
    public void changeTaskStatus(String taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        task.setStatus(status);
        taskRepository.save(task);
    }

    @Override
    public void assignTask(String taskId, AssignUserDto assignUserDto) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        task.setAssignUser(taskMapper.toEntity(assignUserDto));
        taskRepository.save(task);
    }
}
