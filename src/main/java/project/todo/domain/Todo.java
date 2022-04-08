package project.todo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Todo {

    private String name; //필드네임
    private String time;
    private Long id;
    private List<User> userList = new ArrayList<User>();

    public Todo() {
    }

    public Todo(Long id, String name, String time) {
        this.id = id;
        this.name = name; //함수호출 변수 입력받아서 필드네임에 저장
        this.time = time;
    }

    public Long getId() {return id; }
    public String getName() {
        return name; //필드 네임을 리턴
    }
    public String getTime() {return time; }
    public String getReview() {
        return name+" at "+time;
    }
    public List<User> getUserList(){
        return userList;
    }

    public void addUser(User user){
        userList.add(user);

    }


    public void setUser(List<User> userList) {
        for (User user : userList){
            addUser(user);
        } //for문을 이렇게도 쓰는구나
    }
}
