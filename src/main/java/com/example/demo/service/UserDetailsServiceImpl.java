package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.UserModel;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found for name: " + username);
        }
        // ユーザーの権限リストを作成
        List<GrantedAuthority> anthorities = new ArrayList<>();
        anthorities.add((GrantedAuthority) () -> "ROLE_" + user.getRole());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), anthorities);
    }

}
