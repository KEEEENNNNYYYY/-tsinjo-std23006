package com.hei.tsinjo.model;

import java.time.Instant;

public class Donation {
  private String id;
  private Donor donor;
  private Payment payment;
  private Instant createdAt;
}
