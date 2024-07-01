package com.application.moduDeal.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDTO {
    private int chatId;
    private String senderId;
    private String receiverId;
    private int productId;
    private String message;
    private LocalDateTime sendTime;
}
