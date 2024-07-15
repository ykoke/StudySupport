package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.TodoModel;
import com.example.demo.service.TodoService;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todo")
    public String todoList(Model model) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                throw new IllegalArgumentException("Principal is not an instance of UserDetails");
            }

            
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred" + e.getMessage());
        }
        return "todo";
    }

}
