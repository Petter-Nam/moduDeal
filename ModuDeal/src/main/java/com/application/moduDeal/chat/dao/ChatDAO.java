package com.application.moduDeal.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.moduDeal.chat.dto.ChatDTO;

@Mapper
public interface ChatDAO {
    public void insertMessage(ChatDTO chatDTO);
    public List<ChatDTO> selectMessagesByProductId(int productId);
    public String getSellerEmail(String receiverId);
    public String getSellerIdByProductId(int productId);
    public void activateChat(int productId, String receiverId);
}