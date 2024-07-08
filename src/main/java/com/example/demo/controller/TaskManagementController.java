package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.LessonCountdownModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LessonCountdownService;

@Controller
@RequestMapping("")
public class TaskManagementController {
  @Autowired
  private LessonCountdownService lessonCountdownService;

  //@Autowired
  //private LessonCountdownRepository lessonCountdownRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/taskmanagement")
  public String taskManageTop(Model model) {
    try {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            throw new IllegalArgumentException("Principal is not an instance of UserDetails");
        }

        UserModel user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
      List<LessonCountdownModel> lessonCountdownList = lessonCountdownService.listByUserId(user.getId());

      if(lessonCountdownList != null) {
        model.addAttribute("lessonList", lessonCountdownList);
      } else {
        model.addAttribute("error", "No data found");
      }
    }
      catch (Exception e) {
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
  public String lessonInsert(@ModelAttribute LessonCountdownModel lessonCountdownModel, Model model) {
    try {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            throw new IllegalArgumentException("Principal is not an instance of UserDetails");
        }

      lessonCountdownService.insertCountdown(lessonCountdownModel, username);
    } catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "redirect:/taskmanagement";
  }

  @PostMapping("/taskmanagement/delete")
  public String lessonDelete(@ModelAttribute LessonCountdownModel lessonCountdownModel, Model model) {
    try {
      lessonCountdownService.deleteCountdown(lessonCountdownModel.getId());
    } catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "redirect:/taskmanagement";
  }

}