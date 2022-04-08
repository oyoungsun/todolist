package project.todo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.todo.domain.Todo;
import project.todo.domain.TodoRepository;
import project.todo.domain.User;
import project.todo.domain.UserRepository;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }
    public List<Todo> getTodoList() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList;
    }

    public Todo getTodo(Long id){
        Todo todo = todoRepository.findById(id);
        List<User> userList = userRepository.findAllByTodoId(id);
        todo.setUser(userList);
        return todo;
    };

    public void addTodo(Todo todo) {
    }


}
