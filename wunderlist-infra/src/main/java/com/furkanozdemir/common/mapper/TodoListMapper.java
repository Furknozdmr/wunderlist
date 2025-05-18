package com.furkanozdemir.common.mapper;

import com.furkanozdemir.adapter.todolist.entity.TodoList;
import com.furkanozdemir.todolist.port.TodoListDto;
import com.furkanozdemir.user.model.AssignUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoListMapper {

    TodoListMapper INSTANCE = Mappers.getMapper(TodoListMapper.class);

    TodoListDto toDto(TodoList todoList);

    TodoList toEntity(TodoListDto dto);

    AssignUserDto toDto(TodoList.AssignUser user);

    TodoList.AssignUser toEntity(AssignUserDto dto);

    List<TodoListDto> toDtoList(List<TodoList> todoLists);

    List<TodoList> toEntityList(List<TodoListDto> dtoList);
}
