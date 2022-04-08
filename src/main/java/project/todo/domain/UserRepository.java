package project.todo.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {
    List<User> findAllByTodoId(Long todoId);
}
