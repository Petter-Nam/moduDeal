package com.application.moduDeal.chat.service;

import java.util.List;
import java.util.Map;

import com.application.moduDeal.chat.dto.ChatDTO;

public interface ChatService {
	public void sendMessage(ChatDTO chatDTO);

	public List<ChatDTO> getMessagesByProductId(int productId);

	public String getSellerEmail(String receiverId);

	public String fetchReceiverId(int productId);
	
	public void activateChat(Map<String, Object> activeMap);
}
