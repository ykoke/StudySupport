package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ReviewModel;
import com.example.demo.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewModel> findByCourseId(Integer courseId) {
        return reviewRepository.findByCourseId(courseId);
    }

    public void save(ReviewModel review) {
        reviewRepository.save(review);
    }

}