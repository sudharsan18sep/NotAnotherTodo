package com.not.another.todoApp.service;

import com.not.another.todoApp.dto.CreateTodoRequest;
import com.not.another.todoApp.dto.TodoResponse;
import com.not.another.todoApp.entity.Todo;
import com.not.another.todoApp.mapper.TodoMapper;
import com.not.another.todoApp.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //initialises the Mockito annotations for each test.
public class TodoServiceTest {
    //JUnit finds these methods and runs them automatically

    @Mock //
    private TodoMapper todoMapper;

    @Mock //creates a fake dependency. The repository won't connect to MongoDB
    private TodoRepository todoRepository;

    @InjectMocks  //creates your real TodoServiceImpl and injects the mocked dependencies.
    private TodoServiceImpl todoService;

    @Test
    void createTodo_shouldSaveTodoAndReturnResponse(){

        //Arrange
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Unit Testing");
        createTodoRequest.setDescription("Using Mockito");
        Todo todo = new Todo();
        //to check if service class sets it to false when creating
        todo.setCompleted(true);

        TodoResponse expectedTodoResponse = new TodoResponse();

        when(todoMapper.toEntity(createTodoRequest)).thenReturn(todo);
        when(todoRepository.save(todo)).thenReturn(todo);
        when(todoMapper.toResponse(todo)).thenReturn(expectedTodoResponse);

        //ACT

        TodoResponse actualResponse = todoService.createTodo(createTodoRequest);

        assertSame(expectedTodoResponse, actualResponse);
        assertNotNull(todo.getCreatedAt());
        assertFalse(todo.isCompleted());

        //Check whether a mock method was called
        verify(todoMapper).toEntity(createTodoRequest);
        verify(todoMapper).toResponse(todo);
        verify(todoRepository).save(todo);



    }







}
