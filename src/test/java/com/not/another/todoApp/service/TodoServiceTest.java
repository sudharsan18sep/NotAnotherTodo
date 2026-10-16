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

import javax.accessibility.AccessibleTable;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    //returns empty list
    @Test
    void getAllTodos_shouldReturnListofTodos(){

        //Arrange
        List<TodoResponse> expectedResponse = new ArrayList<>();

        when(todoRepository.findAll()).thenReturn(new ArrayList<>());

        //Act

        List<TodoResponse> actualResponse = todoService.getAllTodos();

        //Assert
    //use assertequals if using streams
        assertEquals(expectedResponse, actualResponse);
        verify(todoRepository).findAll();
    }

    @Test
    void getAllTodos_ShouldreturnTwoTodos(){

        //arrange
        List<Todo> expectedResponse = new ArrayList<>();
        for(int i=0; i<2; i++){
            Todo todo = new Todo();
            todo.setTitle("Task"+ i);
            todo.setDescription("Description"+i);
            expectedResponse.add(todo);

            TodoResponse todoResponse = new TodoResponse();
            todoResponse.setTitle(todo.getTitle());
            todoResponse.setDescription(todo.getDescription());
            //Mockito stores two values for two todo
            when(todoMapper.toResponse(todo)).thenReturn(todoResponse);
        }

        when(todoRepository.findAll()).thenReturn(expectedResponse);

        //act
        List<TodoResponse> actualResponse = todoService.getAllTodos();
        //assert
        assertEquals(2, actualResponse.size());
        for(int i=0; i<2;i++) {

            assertEquals(expectedResponse.get(i).getTitle(), actualResponse.get(i).getTitle());
            assertEquals(expectedResponse.get(i).getDescription(), actualResponse.get(i).getDescription());
        }
        verify(todoRepository).findAll();

    }







}
