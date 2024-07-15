package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.LessonCountdownModel;
import com.example.demo.model.TodoModel;
import com.example.demo.service.TodoService;

@Controller
@RequestMapping("")
public class TodoController {
  @Autowired
  private TodoService todoService;
  @GetMapping("/todo")
  public String taskManageTop(Model model) {
    try {
      List<TodoModel> todoList = todoService.listAll();
      

      if(todoList != null) {
        model.addAttribute("todoList", todoList);
      } else {
        model.addAttribute("error", "No data found");
      }
    }
      catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "todo";
  }

    @GetMapping("/todo/todoform")
  public String todoForm(Model model) {
    model.addAttribute("TodoModel", new TodoModel());
    return "todoform";
  }



  @PostMapping("/todo/todoform/todoinsert")
  public String todoInsert(TodoModel todoModel, Model model) {
    try {
     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            throw new IllegalArgumentException("Principal is not an instance of UserDetails");
        }
        todoService.todoinsert(todoModel, username);
    } catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "redirect:/todo";
  }

  @PostMapping("/todo/tododelete/{id}")
  public String lessonDelete(@ModelAttribute LessonCountdownModel todoModel, Model model){
    try {
      todoService.tododelete(todoModel.getId());
    } catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "redirect:/todo";
  }
}