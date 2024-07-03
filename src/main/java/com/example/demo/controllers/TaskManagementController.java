package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.LessonCountdownModel;
import com.example.demo.services.LessonCountdownService;

@Controller
@RequestMapping("")
public class TaskManagementController {
  @Autowired
  private LessonCountdownService lessonCountdownService;

  @GetMapping("/taskmanagement")
  public String taskManageTop(Model model) {
    try {
      List<LessonCountdownModel> lessonCountdownList = lessonCountdownService.listAll();
      if(lessonCountdownList != null) {
        model.addAttribute("lessonList", lessonCountdownList);
      } else {
        model.addAttribute("error", "No data found");
      }
    } catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "taskmanagement";
  }

  @GetMapping("/taskmanagement/form")
  public String taskForm(Model model) {
    model.addAttribute("lessonCountdownModel", new LessonCountdownModel());
    return "taskForm";
  }

  @PostMapping("/taskmanagement/form/insert")
  public String lessonInsert(LessonCountdownModel lessonCountdownModel, Model model) {
    try {
      lessonCountdownService.insert(lessonCountdownModel);
    } catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "redirect:/taskmanagement";
  }

  
}