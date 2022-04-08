package project.todo.domain;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoRepositoryImpl implements TodoRepository {

    private List<Todo> todoLists = new ArrayList<>();

    public TodoRepositoryImpl(){ //생성자
        todoLists.add(new Todo(1004L,"wake up", "2pm"));
        todoLists.add(new Todo(1005L,"wake up2", "2pm"));
        todoLists.add(new Todo(1006L,"wake up3", "2pm"));
    }
    @Override
    public List<Todo> findAll() {
        return todoLists;
    }

    @Override
    public Todo findById(Long id) {
        return todoLists.stream()
                .filter((r -> r.getId().equals(id)))
                .findFirst()
                .orElse(null);
    }
}
