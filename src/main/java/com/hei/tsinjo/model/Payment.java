package com.hei.tsinjo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

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