package com.not.another.todoApp.repository;

import com.not.another.todoApp.entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {
}
