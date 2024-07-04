package com.application.moduDeal.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendChatNotification(String toEmail, String fromUser, String chatLink) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("새로운 채팅 요청이 도착했습니다");
        
        // 로그인 페이지 URL에 리다이렉션 정보를 포함시킵니다.
        String loginLink = "http://www.modudeals.com/moduDeal/login?redirect=" 
            + URLEncoder.encode(chatLink, StandardCharsets.UTF_8);
        
        String messageText = "보낸 사람: " + fromUser + 
            "\n채팅을 시작하려면 다음 링크를 클릭하세요: " + loginLink +
            "\n\n로그인 후 자동으로 채팅 페이지로 이동됩니다.";
        
        mailMessage.setText(messageText);
        mailSender.send(mailMessage);
    }

    public void sendVerificationCode(String email, String verificationCode) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email address is null or empty");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verification Code");
        message.setText("Your verification code is: " + verificationCode);
        mailSender.send(message);
    }
}