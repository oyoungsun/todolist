package project.todo.application;

import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import project.todo.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class TodoServiceTests {
    //지금 의존관계 주입 못하는 상태
    @Mock
    private TodoRepository todoRepository;
    @Mock
    private TodoService todoService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach //모든 테스트 전에 반드시 수행해라
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockTodoRepository();
        mockUserRepository();
        todoService = new TodoService(todoRepository, userRepository);

    }

    private void mockTodoRepository() {
        List<Todo> todoList = new ArrayList<>();
        Todo todo = new Todo(1004L,"wake up", "2pm");
        todo.addUser(new User("oys"));
        todoList.add(todo);
        given(todoRepository.findAll()).willReturn(todoList);
        given(todoRepository.findById(1004L)).willReturn(todo);
    }
    private void mockUserRepository() {
        List<User> userList = new ArrayList<>();
        User user = new User("oys");
        userList.add(user);
        given(userRepository.findAllByTodoId(1004L)).willReturn(userList);
    }

    @Test
    public void getTodo(){
        Todo todo = todoService.getTodo(1004L);

        assertEquals(todo.getId(),1004L);
        User user = todo.getUserList().get(0);
        assertEquals(user.getName(), "oys");
    }
    @Test
    public void getTodoList(){
        List<Todo> todoList = todoService.getTodoList();
        Todo todo = todoList.get(0); //첫번째꺼
        assertEquals(todo.getId(), 1004L);
    }
}