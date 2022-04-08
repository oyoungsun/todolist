package project.todo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.todo.application.TodoService;
import project.todo.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTests {

        @Autowired
        private MockMvc mvc;

        @MockBean
        private TodoService todoService; //moc을 이용한 가짜 객체. controller는 서비스를 직접적으로 사용함

//        //Test의 경우에는 webMvcTest떄문에 직접 의존성 주입해주기
//        @SpyBean(TodoRepositoryImpl.class) //Repo(구현안됨)가 아니라 Impl(구현됨)을 쓰겠다
//        private TodoRepository todoRepository;
//        @SpyBean(UserRepositoryImpl.class)
//        private UserRepository userRepository;
//        @SpyBean(TodoService.class)
//        private TodoService todoService;

        @Test
        public void list() throws Exception {

                List<Todo> todoList = new ArrayList<>();
                todoList.add(new Todo(1004L,"wake up", "2pm"));
                given(todoService.getTodoList()).willReturn(todoList);
                //가짜 서비스가 만들어준다
                //perform으로 확인한다.

                //TodoController todoController = new TodoController();
                mvc.perform(get("/todolist")) //Get 날려서 확인
                    .andExpect(status().isOk())
                    .andExpect(content().string(
                            containsString("wake up")
                    ));

                //System.out.println("this is "+todoController.list().);
                //json : [ {"name":"wake up","time":"2pm","id":1004,"review":"wake up at 2pm"},
                // {"name":"read book","time":"3pm","id":1005,"review":"read book at 3pm"} ]
        }
        @Test
        public void detail() throws Exception {
                Todo todo1 = new Todo(1004L,"wake up", "2pm");
                todo1.addUser(new User("oys"));
                Todo todo2 = new Todo(1005L,"wake up", "2pm");
                todo2.addUser(new User("ous"));
                given(todoService.getTodo(1004L)).willReturn(todo1);
                given(todoService.getTodo(1005L)).willReturn(todo2);

                mvc.perform(get("/todolist/1004")) //{id}자리에 1 넣어주기.
                        .andExpect(status().isOk())
                        .andExpect(content().string(
                                containsString("\"id\":1004")
                        ));
                mvc.perform(get("/todolist/1005")) //{id}자리에 1 넣어주기.
                        .andExpect(status().isOk())
                        .andExpect(content().string(
                                containsString("\"id\":1005")
                        ));
        }

        @Test
        public void create() throws Exception {

                mvc.perform(post("/todolist")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"wakeup\",\"time\":\"2pm\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(header().string("location","/todolist/1234"))
                        .andExpect(content().string("{}")); //json으로 받는다.

                verify(todoService).addTodo(any()); //실행이 잘 되는가 ->verify
        }
}