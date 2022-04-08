package project.todo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//interface
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(){
        return "Hello, world!";
    }
}
