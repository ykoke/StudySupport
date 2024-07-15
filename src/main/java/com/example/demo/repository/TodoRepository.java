package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TodoModel;

@Repository
public interface TodoRepository extends JpaRepository<TodoModel, Integer> {
    List<TodoModel> findByUserId(Integer userId);
}