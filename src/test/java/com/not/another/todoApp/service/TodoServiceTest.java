package com.not.another.todoApp.service;

import com.not.another.todoApp.dto.CreateTodoRequest;
import com.not.another.todoApp.dto.TodoResponse;
import com.not.another.todoApp.entity.Todo;
import com.not.another.todoApp.mapper.TodoMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoServiceTest {
    //JUnit finds these methods and runs them automatically
    @Test
    void shouldMapCreateTodoToTodo(){

        //Arrange : Prepare the input for the test
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();

        createTodoRequest.setTitle("CreateTodo test");
        createTodoRequest.setDescription("toEntity Mapper Testing");
        createTodoRequest.setDeadline(LocalDate.of(2026,10,07));

        //Act: call the testing function

        TodoMapper todoMapper = new TodoMapper();

        Todo todo = todoMapper.toEntity(createTodoRequest);

        //Assert: check the output

        assertEquals("CreateTodo test",todo.getTitle());
        assertEquals("toEntity Mapper Testing", todo.getDescription());
        assertEquals(LocalDate.of(2026,10,07), todo.getDeadline());

    }

    @Test
    void shouldMapTodotoToResponse(){
        //Arrange

        Todo todo = new Todo();
        todo.setTitle("ToResponse Testing");
        todo.setDescription("ToDo to ToResponse");
        todo.setCompleted(false);

        //act

        TodoMapper todoMapper = new TodoMapper();

        TodoResponse todoResponse = todoMapper.toResponse(todo);

        //Assert

        assertEquals("ToResponse Testing", todoResponse.getTitle());
        assertEquals("ToDo to ToResponse", todoResponse.getDescription());
        assertFalse(todoResponse.isCompleted());

    }




}
