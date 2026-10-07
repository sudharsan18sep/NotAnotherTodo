package com.not.another.todoApp.service;

import com.not.another.todoApp.dto.CreateTodoRequest;
import com.not.another.todoApp.dto.TodoResponse;
import com.not.another.todoApp.dto.UpdateTodoRequest;
import com.not.another.todoApp.entity.Todo;
import com.not.another.todoApp.exception.TodoNotFoundException;
import com.not.another.todoApp.mapper.TodoMapper;
import com.not.another.todoApp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoMapper todoMapper;
    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoMapper todoMapper, TodoRepository todoRepository) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponse createTodo(CreateTodoRequest createTodoRequest) {

        Todo todo = todoMapper.toEntity(createTodoRequest);
        todo.setCreatedAt(LocalDateTime.now());
        todo.setCompleted(false);
        TodoResponse todoResponse = todoMapper.toResponse(todoRepository.save(todo));
        return todoResponse;
    }

    @Override
    public List<TodoResponse> getAllTodos() {

        List<Todo> todoList = todoRepository.findAll();
       return todoList.stream().map(x -> todoMapper.toResponse(x)).toList();


    }

    @Override
    public TodoResponse getTodoById(String Id) {
        Optional<Todo> todo = todoRepository.findById(Id);
        if(todo.isPresent()){
            return todoMapper.toResponse(todo.get());

        }
        //later change it to TodoNotFoundException
        throw new TodoNotFoundException("Todo for the given Id:" + Id + "not found");
    }

    @Override
    public TodoResponse updateTodo(UpdateTodoRequest updateTodoRequest) {
        Optional<Todo> todoToUpdate = todoRepository.findById(updateTodoRequest.getId());

        if(todoToUpdate.isPresent()) {
            Todo todo = todoToUpdate.get();
            if (!(updateTodoRequest.getTitle().isBlank())) {
                todo.setTitle(updateTodoRequest.getTitle());
            }

            if (!(updateTodoRequest.getDescription().isBlank())) {
                todo.setDescription(updateTodoRequest.getDescription());
            }

            if (updateTodoRequest.getDeadline()!= null) {
                todo.setDeadline(updateTodoRequest.getDeadline());
            }

            if (updateTodoRequest.isCompleted() !=null) {
                todo.setCompleted(todoToUpdate.get().isCompleted());
            }

            todo.setUpdatedAt(LocalDateTime.now());

            return todoMapper.toResponse(todoRepository.save(todo));

        }
        else throw new RuntimeException();



    }

    @Override
    public void deleteTodo(String Id) {

        Todo todo = todoRepository.findById(Id).orElseThrow(()->new TodoNotFoundException("Todo for the given Id:" + Id + "not found"));
        todoRepository.delete(todo);

    }
}


