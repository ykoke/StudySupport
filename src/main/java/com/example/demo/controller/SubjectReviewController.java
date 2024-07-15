package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.CourseModel;
import com.example.demo.model.ReviewModel;
import com.example.demo.service.CourseService;
import com.example.demo.service.ReviewService;


@Controller
public class SubjectReviewController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/subjectreview")
    public String course(Model model) {
        try {
            List<CourseModel> courses = courseService.listAll();
            if(courses.isEmpty()) {
                model.addAttribute("message", "No courses found.");
            } else {
                model.addAttribute("courses", courses);
            }
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "subjectreview";
    }

    @GetMapping("/subjectreview/{courseId}/reviews")
    public String reviews(@PathVariable("courseId") Integer courseId, Model model) {
        try {
            CourseModel course = courseService.findById(courseId);
            if(course == null) {
                model.addAttribute("message", "Course not found.");
            } else {
                List<ReviewModel> reviews = reviewService.findByCourseId(courseId);
                if(reviews.isEmpty()) {
                    model.addAttribute("message", "No reviews found.");
                } else {
                    model.addAttribute("course", course);
                    model.addAttribute("reviews", reviews);
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "review";
    }

    @PostMapping("/subjectreview/{courseId}/reviews/insert")
    public String insertReview(@PathVariable("courseId") Integer courseId, @RequestParam("rating") Integer rating, @RequestParam("review") String review, Model model) {
        try {
            CourseModel course = courseService.findById(courseId);
            if(course == null) {
                model.addAttribute("message", "Course not found.");
            } else {
                ReviewModel reviewModel = new ReviewModel();
                reviewModel.setCourseId(courseId);
                reviewModel.setRating(rating);
                reviewModel.setReview(review);
                reviewModel.setCreatedAt(new Date());
                reviewService.save(reviewModel);
                model.addAttribute("message", "Review added successfully.");
            }
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "redirect:/subjectreview/{courseId}/reviews";
    }
}