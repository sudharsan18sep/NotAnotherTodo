package com.not.another.todoApp.service;

import com.not.another.todoApp.dto.CreateTodoRequest;
import com.not.another.todoApp.dto.TodoResponse;
import com.not.another.todoApp.dto.UpdateTodoRequest;

import java.util.List;

public class TodoServiceImpl implements TodoService{
    @Override
    public TodoResponse createTodo(CreateTodoRequest createTodoRequest) {
        return null;
    }

    @Override
    public List<TodoResponse> getAllTodos() {
        return List.of();
    }

    @Override
    public TodoResponse getTodoById(String Id) {
        return null;
    }

    @Override
    public TodoResponse updateTodo(UpdateTodoRequest updateTodoRequest) {
        return null;
    }

    @Override
    public void deleteTodo(String Id) {

    }
}
