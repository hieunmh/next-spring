package com.hieunmh.server.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hieunmh.server.model.ChatMessage;
import com.hieunmh.server.model.Notification;
import com.hieunmh.server.service.ChatMessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
  
  private final SimpMessagingTemplate messagingTemplate;
  private final ChatMessageService chatMessageService;

  @SuppressWarnings("null")
  @MessageMapping("/chat")
  public void processMessage(@Payload ChatMessage chatMessage) {
    ChatMessage savedMsg = chatMessageService.save(chatMessage);
    messagingTemplate.convertAndSendToUser(
      chatMessage.getReceiveID(), 
      "/queue/messages", 
      Notification.builder().id(savedMsg.getId())
      .receiveID(savedMsg.getReceiveID())
      .content(savedMsg.getContent())
      .build()
    );
  }

  @GetMapping("/messages/{senderID}/{receiverID}")
  public ResponseEntity<List<ChatMessage>> findChatMessage(
    @PathVariable("senderID") String senderID,
    @PathVariable("receiveID") String receiveID
  ) {
    return ResponseEntity.ok(chatMessageService.findChatMessages(senderID, receiveID));
  }
}
