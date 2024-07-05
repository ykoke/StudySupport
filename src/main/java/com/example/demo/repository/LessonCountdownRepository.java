package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LessonCountdownModel;

@Repository
public interface LessonCountdownRepository extends JpaRepository<LessonCountdownModel, Integer> {

}