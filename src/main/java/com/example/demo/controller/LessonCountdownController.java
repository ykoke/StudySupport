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
public class LessonCountdownController {
  @Autowired
  private LessonCountdownService lessonCountdownService;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/lessoncountdown")
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
    return "lessoncountdown";
  }

    @GetMapping("/lessoncountdown/countdownform")
  public String countdownform(Model model) {
    model.addAttribute("lessonCountdownModel", new LessonCountdownModel());
    return "countdownform";
  }


  @PostMapping("/lessoncountdown/countdownform/countdowninsert")
  public String lessonInsert(@ModelAttribute LessonCountdownModel lessonCountdownModel, Model model) {
    try {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            throw new IllegalArgumentException("Principal is not an instance of UserDetails");
        }

      lessonCountdownService.countdowninsert(lessonCountdownModel, username);
    } catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "redirect:/lessoncountdown";
  }

  @PostMapping("/lessoncountdown/countdowndelete/{id}")
  public String lessonDelete(@ModelAttribute LessonCountdownModel lessonCountdownModel, Model model) {
    try {
      lessonCountdownService.countdowndelete(lessonCountdownModel.getId());
    } catch (Exception e) {
      model.addAttribute("error", "An error occurred" + e.getMessage());
    }
    return "redirect:/lessoncountdown";
  }

}