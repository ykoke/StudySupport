package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LessonCountdownModel;

public interface LessonCountdownRepository extends JpaRepository<LessonCountdownModel, Integer> {
    List<LessonCountdownModel> findByUserId(Integer userId);
}