package com.not.another.todoApp.controller;

import com.not.another.todoApp.dto.CreateTodoRequest;
import com.not.another.todoApp.dto.TodoResponse;
import com.not.another.todoApp.dto.UpdateTodoRequest;
import com.not.another.todoApp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/v1")
public class TodoController {

    private TodoService todoService;

    TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    //create a Todo
    @PostMapping()
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody CreateTodoRequest createTodoRequest)
    {
        TodoResponse todoResponse = todoService.createTodo(createTodoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(todoResponse);

    }

    //update todo
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable String id ,@Valid @RequestBody UpdateTodoRequest updateTodoRequest)
    {
        updateTodoRequest.setId(id);
        TodoResponse todoResponse = todoService.updateTodo(updateTodoRequest);

        return ResponseEntity.status(HttpStatus.OK).body(todoResponse);
    }


    //get todo by ID
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable String id){
        TodoResponse todoResponse = todoService.getTodoById(id);

        return ResponseEntity.ok().body(todoResponse);
    }

    //get all todo
    @GetMapping("/all")
    public ResponseEntity<List<TodoResponse>> getAllTodo(){


        return ResponseEntity.ok(todoService.getAllTodos());
    }

    //delete todo by Id
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodobyId (@PathVariable String id){
        todoService.deleteTodo(id);

        return ResponseEntity.ok().build();
    }

}
