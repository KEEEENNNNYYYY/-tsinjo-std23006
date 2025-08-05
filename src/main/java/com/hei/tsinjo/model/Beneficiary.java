package com.hei.tsinjo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Beneficiary {
  private String id;
  private Long balance;
  private String name;
  private String email;
}
