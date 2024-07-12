package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoModel;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.UserModel;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TodoService {
    @Autowired
    private TodoRepository TodoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TodoModel> listAll() {
        return TodoRepository.findAll();
    }

    public List<TodoModel> listByUserId(Integer userId) {
        return TodoRepository.findByUserId(userId);
    }

    public void insertTodo(TodoModel TodoModel, String username) {
        UserModel user = userRepository.findByUsername(username);
        TodoModel.setUserId(user.getId());
        TodoRepository.save(TodoModel);
    }
}