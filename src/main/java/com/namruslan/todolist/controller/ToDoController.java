package com.namruslan.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToDoController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/todo")
    public String todoPage() {
        return "todo";
    }

}



