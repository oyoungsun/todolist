package project.todo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.todo.application.TodoService;
import project.todo.domain.Todo;
import project.todo.domain.TodoRepository;
import project.todo.domain.User;
import project.todo.domain.UserRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@RestController //컴포넌트를 spring이 관리하고 있음.
public class TodoController {

//        @Autowired
//        private TodoRepository todoRepository;
//                //@auto wired = new TodoRepository(); 를 spring이 관리
//        @Autowired
//        private UserRepository userRepository;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todolist")
    public List<Todo> list(){
        List<Todo> todoLists = todoService.getTodoList();

        return todoLists;
    }

    @GetMapping("/todolist/{id}")
    public Todo detail(@PathVariable("id") Long id){ //주소에서의 parameter를 함수 내에서 쓰려면
        Todo todo = todoService.getTodo(id);
//        //id 받을 때 List에서 조회를 할 수 있게
//        Todo todo = todoRepository.findById(id);
//        List<User> userList = userRepository.findAllByTodoId(id);
//        //해당 todo의 참가자 조회하기
//        todo.setUser(userList);
//        //현재 DB쓰고 있지 않아서 여기서 저장하는거
//       todo.addUser(new User("oys"));
//        //id로 List내 서치 id가 같은 것 중 첫번째 것을 가져오기기
        return todo;
    }
    @PostMapping("/todolist")
    public ResponseEntity<?> create(@RequestBody Todo resource) throws URISyntaxException {
        String name = resource.getName();
        String time = resource.getTime();
        Todo todo = new Todo(1234L, name, time);
        todoService.addTodo(todo);
        URI location = new URI("/todolist/"+todo.getId());
        return ResponseEntity.created(location).body("{}");
        //expected : 201 Created::요청이 성공적으로 처리되고 자원이 생성됨
    }
}
//list, detail에서 둘다 list에서 가져옴. 이 list를 관리하는 것이 repository가 됨.
