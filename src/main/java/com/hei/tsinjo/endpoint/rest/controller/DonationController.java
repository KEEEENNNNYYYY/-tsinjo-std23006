package com.hei.tsinjo.endpoint.rest.controller;

import com.hei.tsinjo.model.Donation;
import com.hei.tsinjo.model.Payment;
import com.hei.tsinjo.model.PaymentStatus;
import com.hei.tsinjo.repository.InMemoryPaymentRepository;
import com.hei.tsinjo.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class DonationController {
    private final PaymentService paymentService;
    private final InMemoryPaymentRepository paymentRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("payments", paymentRepository.findAll());
        model.addAttribute("newPayment", Payment.builder().build());
        return "index";
    }

    @PostMapping("/donate")
    public String makeDonation(@ModelAttribute Payment payment) {
        // Générer un ID et setter les valeurs par défaut
        payment.setId(UUID.randomUUID().toString());
        payment.setPaymentDate(Instant.now());
        payment.setStatus(PaymentStatus.VERIFYING);

        try {
            // Envoyer le paiement à Vola
            String volaPaymentId = paymentService.sendPayment(payment);
            payment.setVolaPaymentId(volaPaymentId);

            // Démarrer la vérification asynchrone
            paymentService.verifyPaymentAsync(payment);

            // Sauvegarder en mémoire
            paymentRepository.save(payment);
        } catch (Exception e) {
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
        }

        return "redirect:/";
    }
}