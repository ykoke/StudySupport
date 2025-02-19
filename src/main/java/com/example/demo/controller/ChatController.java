package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.ChatMessageModel;


@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageModel sendMessage(@Payload ChatMessageModel chatMessage) {
        if (chatMessage.getImage() != null) {
            String imgTag = "<img src='" + chatMessage.getImage() + "' alt='Image from " + chatMessage.getSender() + "' />";
            chatMessage.setContent(chatMessage.getContent() + imgTag); // 画像タグをメッセージに追加
        }
        return chatMessage;
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            throw new IllegalArgumentException("Principal is not an instance of UserDetails");
        }
        model.addAttribute("username", username);
        return "chat";
    }
    

}