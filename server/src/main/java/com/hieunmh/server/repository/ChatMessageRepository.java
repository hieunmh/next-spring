package com.hieunmh.server.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hieunmh.server.model.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
  List<ChatMessage> findByChatID(String s);
}
