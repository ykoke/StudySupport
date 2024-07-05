package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoModel;
import com.example.demo.repository.TodoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TodoService {
    @Autowired
    private TodoRepository TodoRepository;

    public List<TodoModel> listAll() {
        return TodoRepository.findAll();
    }

    public void insert(TodoModel TodoModel) {
        TodoRepository.save(TodoModel);
    }
}