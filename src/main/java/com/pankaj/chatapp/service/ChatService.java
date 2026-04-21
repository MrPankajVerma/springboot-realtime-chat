package com.pankaj.chatapp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pankaj.chatapp.entity.ChatMessage;
import com.pankaj.chatapp.entity.User;
import com.pankaj.chatapp.repository.ChatMessageRepository;
import com.pankaj.chatapp.repository.UserRepository;

@Service
public class ChatService {
	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ChatMessage sendMessage(ChatMessage message) {
		
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		User sender = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		message.setSenderId(sender.getId());
		message.setTimestamp(LocalDateTime.now());
		
		return chatMessageRepository.save(message);
	}
}
