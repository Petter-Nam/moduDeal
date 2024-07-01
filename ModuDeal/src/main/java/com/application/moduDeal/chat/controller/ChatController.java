package com.application.moduDeal.chat.controller;

import com.application.moduDeal.chat.dto.ChatDTO;
import com.application.moduDeal.chat.service.ChatService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatService chatService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/chat")
    public String chatPage(@RequestParam("productId") int productId, @RequestParam("userId") String userId, Model model) {
        model.addAttribute("productId", productId);
        model.addAttribute("userId", userId);
        String receiverId = chatService.fetchReceiverId(productId);
        model.addAttribute("receiverId", receiverId);
        
        // 구매자가 채팅을 시작하면 판매자에게 이메일을 보냅니다.
        String sellerEmail = chatService.getSellerEmail(receiverId);
        String chatLink = "http://localhost/chat/activate?productId=" + productId + "&receiverId=" + receiverId;
        emailService.sendChatNotification(sellerEmail, userId, chatLink);
        
        return "/moduDeal/chat";
    }
    
    @GetMapping("/chat/activate")
    @ResponseBody
    public String activateChat(@RequestParam("productId") int productId, @RequestParam("receiverId") String receiverId) {
        // 채팅 활성화 로직을 구현합니다.
        chatService.activateChat(productId, receiverId);
        return "채팅이 활성화되었습니다. 이제 채팅을 시작할 수 있습니다.";
    }
    
    @MessageMapping("/chat")
    public void sendMessage(ChatDTO chatDTO) {
        if (chatDTO.getReceiverId() == null) {
            throw new IllegalArgumentException("수신자 ID는 필수입니다.");
        }
        chatService.sendMessage(chatDTO);
        messagingTemplate.convertAndSend("/topic/messages", chatDTO);

        // 이메일로 알림 보내기
        String sellerEmail = chatService.getSellerEmail(chatDTO.getReceiverId());
        emailService.sendChatNotification(sellerEmail, chatDTO.getSenderId(), chatDTO.getMessage());
    }

    @GetMapping("/chat/messages/{productId}")
    @ResponseBody
    public List<ChatDTO> getMessagesByProductId(@PathVariable int productId) {
        return chatService.getMessagesByProductId(productId);
    }
}
