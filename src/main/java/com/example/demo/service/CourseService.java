package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CourseModel;
import com.example.demo.repository.CourseRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseModel> listAll() {
        return courseRepository.findAll();
    }

    public CourseModel findById(Integer courseId) {
        return courseRepository.findById(courseId).get();
    }

}
