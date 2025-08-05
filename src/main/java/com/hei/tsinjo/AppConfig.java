package com.hei.tsinjo;

import com.hei.tsinjo.repository.InMemoryPaymentRepository;
import com.hei.tsinjo.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public PaymentService paymentService() {
    return new PaymentService();
  }

  @Bean
  public InMemoryPaymentRepository paymentRepository() {
    return new InMemoryPaymentRepository();
  }
}
