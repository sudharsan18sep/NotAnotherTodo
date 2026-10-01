package com.not.another.todoApp.mapper;

import com.not.another.todoApp.dto.CreateTodoRequest;
import com.not.another.todoApp.dto.TodoResponse;
import com.not.another.todoApp.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo toEntity(CreateTodoRequest createTodoRequest){
        Todo todo = new Todo();
        todo.setTitle(createTodoRequest.getTitle());
        todo.setDescription(createTodoRequest.getDescription());
        todo.setDeadline(createTodoRequest.getDeadline());
        return todo;
    }

    public TodoResponse toResponse(Todo todo){
        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setId(todo.getId());
        todoResponse.setTitle(todo.getTitle());
        todoResponse.setDescription(todo.getDescription());
        todoResponse.setCompleted(todo.isCompleted());

        return todoResponse;
    }
}
