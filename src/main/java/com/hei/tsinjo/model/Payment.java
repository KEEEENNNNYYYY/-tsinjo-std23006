package com.hei.tsinjo.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Payment {
  private String id;
  private double amount;
  private String method;
  private Instant paymentDate;
  private PaymentStatus status;
  private String receiver;
  private String volaPaymentId;
}
