package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LessonCountdownModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.LessonCountdownRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LessonCountdownService {
    @Autowired
    private LessonCountdownRepository lessonCountdownRepository;

    @Autowired
    private UserRepository userRepository;

    public List<LessonCountdownModel> listAll() {
        return lessonCountdownRepository.findAll();
    }

    public List<LessonCountdownModel> listByUserId(Integer userId) {
        return lessonCountdownRepository.findByUserId(userId);
    }

    public void insertCountdown(LessonCountdownModel lessonCountdownModel, String username) {
        UserModel user = userRepository.findByUsername(username);
        lessonCountdownModel.setUserId(user.getId());
        lessonCountdownRepository.save(lessonCountdownModel);
    }

    public void deleteCountdown(Integer id) {
        lessonCountdownRepository.deleteById(id);
    }
    
}