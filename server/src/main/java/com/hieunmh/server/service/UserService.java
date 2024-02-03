package com.hieunmh.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hieunmh.server.model.Status;
import com.hieunmh.server.repository.UserRepository;
import com.hieunmh.server.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public void saveUser(User user) {
    user.setStatus(Status.ONLINE);
    repository.save(user);
  }

  @SuppressWarnings("null")
  public void disconect(User user) {
    var storedUser = repository.findById(user.getNickname()).orElse(null);

    if (storedUser != null) {
      storedUser.setStatus(Status.OFFLINE);
      repository.save(storedUser);
    }
  }

  public List<User> findConnectedUsers() {
    return repository.findAllByStatus(Status.ONLINE);
  }
}
