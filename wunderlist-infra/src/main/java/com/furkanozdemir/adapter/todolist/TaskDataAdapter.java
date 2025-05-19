package com.furkanozdemir.adapter.todolist;

import com.furkanozdemir.adapter.todolist.entity.Task;
import com.furkanozdemir.adapter.todolist.entity.TodoList;
import com.furkanozdemir.adapter.todolist.repository.TaskRepository;
import com.furkanozdemir.adapter.todolist.repository.TodoListRepository;
import com.furkanozdemir.common.exception.TaskNotFoundException;
import com.furkanozdemir.common.mapper.TaskMapper;
import com.furkanozdemir.common.mapper.TodoListMapper;
import com.furkanozdemir.todolist.port.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deleteSubTaskById(String id) {
        //TODO silme yapılacak
    }

    @Override
    public void createTask(TaskDto taskDto) {
        taskRepository.save(taskMapper.toEntity(taskDto));
    }

    @Override
    public void createSubTask(SubTaskDto subTaskDto) {
        var subTask = taskMapper.toEntity(subTaskDto);
        //TODO save
    }
}
