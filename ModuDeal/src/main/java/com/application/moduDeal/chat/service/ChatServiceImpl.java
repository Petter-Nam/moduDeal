package com.application.moduDeal.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.moduDeal.chat.dao.ChatDAO;
import com.application.moduDeal.chat.dto.ChatDTO;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDAO chatDAO;

    @Override
    public void sendMessage(ChatDTO chatDTO) {
        chatDAO.insertMessage(chatDTO);
    }

    @Override
    public List<ChatDTO> getMessagesByProductId(int productId) {
        return chatDAO.selectMessagesByProductId(productId);
    }

    @Override
    public String getSellerEmail(String receiverId) {
        return chatDAO.getSellerEmail(receiverId);
    }

    @Override
    public String fetchReceiverId(int productId) {
        return chatDAO.getSellerIdByProductId(productId);
    }
    
    @Override
    public void activateChat(int productId, String receiverId) {
        // 채팅 활성화 로직을 구현합니다. 예를 들어 데이터베이스에 활성화 상태를 업데이트합니다.
        chatDAO.activateChat(productId, receiverId);
    }
}