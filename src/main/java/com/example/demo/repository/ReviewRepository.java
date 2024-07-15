package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ReviewModel;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel, Integer> {
    List<ReviewModel> findByCourseId(Integer courseId);
}