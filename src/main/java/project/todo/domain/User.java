package project.todo.domain;

import java.time.LocalDateTime;

public class User {
    public final String name;

    public User(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
