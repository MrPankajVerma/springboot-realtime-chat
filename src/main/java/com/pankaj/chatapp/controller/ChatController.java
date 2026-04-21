package com.pankaj.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.chatapp.entity.ChatMessage;
import com.pankaj.chatapp.service.ChatService;

@RestController
@RequestMapping("api/chat")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@PostMapping("/send")
	public ChatMessage sendMessage(@RequestBody ChatMessage message) {
		return chatService.sendMessage(message);
	}

}
