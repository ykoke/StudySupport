package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
      todoService.todoinsert(todoModel);
    } catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "redirect:/todo";
  }

  @PostMapping("/todo/tododelete/{id}")
  public String lessonDelete(@PathVariable("id") Integer id, Model model) {
    try {
      todoService.tododelete(id);
    } catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "redirect:/todo";
  }
}