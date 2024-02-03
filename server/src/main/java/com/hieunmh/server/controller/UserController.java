package com.hieunmh.server.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hieunmh.server.model.User;
import com.hieunmh.server.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @MessageMapping("/user.addUser")
  @SendTo("/user/topic")
  public User addUser(@Payload User user) {
    service.saveUser(user);
    return user;
  }

  @MessageMapping("/user.disconnectUser")
  @SendTo("/user/topic")
  public User disconnect(@Payload User user) {
    service.disconect(user);
    return user;
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> findConnectedUser() {
    return ResponseEntity.ok(service.findConnectedUsers());
  }

}
