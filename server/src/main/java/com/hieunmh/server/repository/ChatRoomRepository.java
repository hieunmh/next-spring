package com.hieunmh.server.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hieunmh.server.model.ChatRoom;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
  Optional<ChatRoom> findBySenderIDAndReceiveID(String senderID, String receiveID);
}
