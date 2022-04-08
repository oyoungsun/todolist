package project.todo.domain;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();

    Todo findById(Long id);
}
