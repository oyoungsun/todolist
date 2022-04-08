package project.todo.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {
    private List<User> userList = new ArrayList<User>();

    public UserRepositoryImpl(){
        userList.add(new User("oys"));
    }
    @Override
    public List<User> findAllByTodoId(Long todoId) {
        return userList;
    }
}
