package com.application.moduDeal.chat.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendChatNotification(String toEmail, String fromUser, String chatLink) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("새로운 채팅 요청이 도착했습니다");
        String fullLink = "http://www.modudeals.com/moduDeal/login?redirectUrl=" + URLEncoder.encode(chatLink, StandardCharsets.UTF_8);
        mailMessage.setText("보낸 사람: " + fromUser + "\n채팅을 시작하려면 다음 링크를 클릭하세요: " + fullLink);
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