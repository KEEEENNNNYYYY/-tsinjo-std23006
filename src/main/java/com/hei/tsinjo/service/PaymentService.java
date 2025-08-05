package com.hei.tsinjo.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.hei.tsinjo.model.Payment;
import com.hei.tsinjo.model.PaymentStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final String API_URL = "https://42cwka3n4ifcp7ufheyrpmph240iuaxo.lambda-url.eu-west-3.on.aws/v3/payments";
    private static final String API_KEY = "CLÉ_API_ICI";

    public String sendPayment(Payment payment) throws IOException, JSONException {
        // Préparer la connexion HTTP POST
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setDoOutput(true);

        // Créer le JSON à partir de l’objet payment
        JSONObject json = new JSONObject();
        json.put("amount", payment.getAmount());
        json.put("receiver", payment.getReceiver());

        // Écrire le corps de la requête
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = json.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Lire la réponse
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        // Extraire paymentId depuis la réponse JSON
        JSONObject responseJson = new JSONObject(response.toString());
        return responseJson.getString("paymentId");
    }

    public PaymentStatus checkPaymentStatus(String volaPaymentId) throws IOException, JSONException {
        URL url = new URL(API_URL + "/" + volaPaymentId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        JSONObject responseJson = new JSONObject(response.toString());
        String status = responseJson.getString("status");

        return switch (status) {
            case "SUCCEEDED" -> PaymentStatus.SUCCEEDED;
            case "FAILED" -> PaymentStatus.FAILED;
            default -> PaymentStatus.VERIFYING;
        };
    }

    public void verifyPaymentAsync(Payment payment) {
        new Thread(() -> {
            try {
                while (true) {
                    PaymentStatus status = checkPaymentStatus(payment.getVolaPaymentId());
                    payment.setStatus(status);

                    if (status != PaymentStatus.VERIFYING) {
                        break;
                    }

                    TimeUnit.SECONDS.sleep(5);
                }
            } catch (Exception e) {
                e.printStackTrace();
                payment.setStatus(PaymentStatus.FAILED);
            }
        }).start();
    }
}
