package com.application.moduDeal.chat.controller;

import com.application.moduDeal.chat.dto.ChatDTO;
import com.application.moduDeal.chat.service.ChatService;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
    public String chatPage(
        @RequestParam("productId") int productId,
        @RequestParam("receiverId") String receiverId,
        @RequestParam("senderId") String senderId,
        Model model,
        HttpSession session
    ) {
        // 로그인 상태 확인
        if (session.getAttribute("userId") == null) {
            // 로그인 상태가 아닌 경우 로그인 페이지로 리디렉션
            return "redirect:/moduDeal/login";
        }

        // 로그인 상태인 경우 기존 채팅 로직 실행
        model.addAttribute("productId", productId);
        model.addAttribute("receiverId", receiverId);
        model.addAttribute("senderId", senderId);

        // 채팅 링크를 생성
        String chatLink = "http://www.modudeals.com:80/chat?productId=" + productId
                + "&receiverId=" + receiverId
                + "&senderId=" + senderId;

            String sellerEmail = chatService.getSellerEmail(receiverId);
            emailService.sendChatNotification(sellerEmail, senderId, chatLink);

        return "moduDeal/chat"; // chat.html 템플릿으로 직접 이동
    }


    
    @GetMapping("/chat/activate")
    public String activateChat(
        @RequestParam("productId") int productId, 
        @RequestParam("receiverId") String receiverId,
        @RequestParam("senderId") String senderId // 추가된 부분
    ) {

        // 채팅 활성화 로직을 구현합니다.
        Map<String, Object> activeMap = new HashMap<>();
        activeMap.put("productId", productId);
        activeMap.put("receiverId", receiverId);
        activeMap.put("senderId", senderId); // 추가된 부분

        chatService.activateChat(activeMap);

        return "moduDeal/chat"; // 채팅 페이지로 이동
    }

    
    @MessageMapping("/chat/send")
    @SendTo("/topic/messages")
    public ChatDTO sendMessage(ChatDTO chatDTO) {
        if (chatDTO.getReceiverId() == null || chatDTO.getSenderId() == null) {
            throw new IllegalArgumentException("송신자 ID와 수신자 ID는 필수입니다.");
        }

        chatService.sendMessage(chatDTO);



        return chatDTO;
    }


    @GetMapping("/chat/messages/{productId}")
    @ResponseBody
    public List<ChatDTO> getMessagesByProductId(@PathVariable int productId) {
        return chatService.getMessagesByProductId(productId);
    }
}
