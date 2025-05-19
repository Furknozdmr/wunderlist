package com.furkanozdemir.common.mapper;

import com.furkanozdemir.adapter.todolist.entity.AssignUser;
import com.furkanozdemir.adapter.todolist.entity.SubTask;
import com.furkanozdemir.adapter.todolist.entity.Task;
import com.furkanozdemir.todolist.port.SubTaskDto;
import com.furkanozdemir.todolist.port.TaskDto;
import com.furkanozdemir.user.model.AssignUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto toDto(Task Task);

    Task toEntity(TaskDto dto);

    AssignUserDto toDto(AssignUser user);

    AssignUser toEntity(AssignUserDto dto);

    SubTaskDto toDto(SubTask subTask);

    SubTask toEntity(SubTaskDto dto);

    List<TaskDto> toDtoList(List<Task> Tasks);

    List<Task> toEntityList(List<TaskDto> dtoList);
}
