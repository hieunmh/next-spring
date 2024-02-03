package com.hieunmh.server.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hieunmh.server.model.ChatRoom;
import com.hieunmh.server.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

  private final ChatRoomRepository chatRoomRepository;

  public Optional<String> getChatRoomID(String senderID, String receiveID, boolean createNewRoomIfNotExist) {
    return chatRoomRepository.findBySenderIDAndReceiveID(senderID, receiveID)
    .map(ChatRoom::getChatID)
    .or(() -> {
      if (createNewRoomIfNotExist) {
        var chatID = createChat(senderID, receiveID);
        return Optional.of(chatID);
      }

      return Optional.empty();
    });
  }

  @SuppressWarnings("null")
  private String createChat(String senderID, String receiveID) {
    var chatID = String.format("%s_%s",senderID, receiveID);

    ChatRoom senderReceiver = ChatRoom.builder().chatID(chatID).senderID(senderID).receiveID(receiveID).build();
    ChatRoom receiveReceiver = ChatRoom.builder().chatID(chatID).senderID(senderID).receiveID(receiveID).build();

    chatRoomRepository.save(senderReceiver);
    chatRoomRepository.save(receiveReceiver);


    return chatID;
  }

}
