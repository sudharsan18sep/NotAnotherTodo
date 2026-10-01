package com.not.another.todoApp.service;

import com.not.another.todoApp.dto.CreateTodoRequest;
import com.not.another.todoApp.dto.TodoResponse;
import com.not.another.todoApp.dto.UpdateTodoRequest;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface TodoService {

    TodoResponse createTodo(CreateTodoRequest createTodoRequest);

    List<TodoResponse> getAllTodos();

    TodoResponse getTodoById(String Id);

    TodoResponse updateTodo(UpdateTodoRequest updateTodoRequest);


   void deleteTodo(String Id);


}
