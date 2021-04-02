package com.namruslan.todolist.controller;

import com.namruslan.todolist.repr.ToDoRepr;
import com.namruslan.todolist.service.ToDoService;
import com.namruslan.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ToDoController {

    private ToDoService toDoService;

    private UserService userService;

    @Autowired
    public ToDoController(ToDoService toDoService, UserService userService) {
        this.toDoService = toDoService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        List<ToDoRepr> todos = toDoService.findTodosByUserId(userService.getCurrentUserId().orElseThrow(ResourceNotFoundException::new));
        model.addAttribute("todos", todos);
        return "index";
    }

    @GetMapping("/todo/{id}")
    public String todoPage(@PathVariable("id") Long id, Model model) {
        ToDoRepr toDoRepr = toDoService.findById(id).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("todo", toDoRepr);
        return "todo";
    }

    @GetMapping("/todo/create")
    public String createTodoPage(Model model) {
        model.addAttribute("todo", new ToDoRepr());
        return "todo";
    }

    @PostMapping("/todo/create")
    public String createTodoPost(@ModelAttribute("todo") @Valid ToDoRepr toDoRepr,
                                 BindingResult result) {
        if (result.hasErrors()) return "todo";

        toDoService.save(toDoRepr);
        return "redirect:/";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        toDoService.delete(id);
        return "redirect:/";
    }

}



