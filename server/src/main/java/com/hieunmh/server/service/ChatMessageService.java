package com.hieunmh.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hieunmh.server.model.ChatMessage;
import com.hieunmh.server.repository.ChatMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
  
  private final ChatMessageRepository repository;
  private final ChatRoomService chatRoomService;

  public ChatMessage save(ChatMessage chatMessage) {
    var chatID = chatRoomService.getChatRoomID(
      chatMessage.getSenderID(), 
      chatMessage.getReceiveID(), 
      true
    ).orElseThrow();

    chatMessage.setChatID(chatID);

    repository.save(chatMessage);
    return chatMessage;
  }

  public List<ChatMessage> findChatMessages(String senderID, String receiverID) {
    var chatID = chatRoomService.getChatRoomID(senderID, receiverID, false);

    return chatID.map(repository::findByChatID).orElse(new ArrayList<>());
  }
}
