package com.furkanozdemir.adapter.todolist;

import com.furkanozdemir.adapter.todolist.entity.TodoList;
import com.furkanozdemir.adapter.todolist.repository.TodoListRepository;
import com.furkanozdemir.common.mapper.TodoListMapper;
import com.furkanozdemir.todolist.port.TodoListDto;
import com.furkanozdemir.todolist.port.TodoListPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoListDataAdapter implements TodoListPort {

    private final TodoListRepository todoListRepository;

    private final TodoListMapper todoListMapper;

    @Override
    public List<TodoListDto> getAllTodoListsByUserId(String userId) {
        List<TodoList> todoListsByAssignUserIdsIn = todoListRepository.findTodoListsByAssignUserUserId(userId);
        return todoListMapper.toDtoList(todoListsByAssignUserIdsIn);
    }

    @Override
    public void deleteTodoListById(String todoListId) {
        todoListRepository.deleteById(todoListId);
    }

    @Override
    public void createTodoList(TodoListDto todoListDto) {
        todoListRepository.save(todoListMapper.toEntity(todoListDto));
    }
}
