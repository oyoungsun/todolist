package project.todo.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTests {

    @Test //Todo domain에 변수 추가할 때마다 Test해보기
    public void create() {
        Todo todo = new Todo(1004L,"wake up", "2pm");
        assertEquals(todo.getId(), 1004L);
        assertEquals(todo.getName(), "wake up");
        assertEquals(todo.getTime(), "2pm");
    }

    @Test
    public void review() {
        Todo todo = new Todo(1004L,"wake up", "2pm");
        assertEquals(todo.getReview(), "wake up at 2pm");
    }
}