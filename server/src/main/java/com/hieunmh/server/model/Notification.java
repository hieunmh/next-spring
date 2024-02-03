package com.hieunmh.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

  private String id;
  private String senderID;
  private String receiveID;
  private String content;
}
