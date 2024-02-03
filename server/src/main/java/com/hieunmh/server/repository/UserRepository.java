package com.hieunmh.server.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hieunmh.server.model.Status;
import com.hieunmh.server.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  
  List<User> findAllByStatus(Status status);
}
