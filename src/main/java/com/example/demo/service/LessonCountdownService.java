package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LessonCountdownModel;
import com.example.demo.repository.LessonCountdownRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LessonCountdownService {
    @Autowired
    private LessonCountdownRepository lessonCountdownRepository;

    public List<LessonCountdownModel> listAll() {
        return lessonCountdownRepository.findAll();
    }

    public void insert(LessonCountdownModel lessonCountdownModel) {
        lessonCountdownRepository.save(lessonCountdownModel);
    }

    public void delete(Integer id) {
        lessonCountdownRepository.deleteById(id);
    }

    public void update(LessonCountdownModel lessonCountdownModel) {
        lessonCountdownRepository.save(lessonCountdownModel);
    }
}