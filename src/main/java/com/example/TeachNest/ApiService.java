package com.example.TeachNest;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 600000) // Call API every 60 seconds (1 minute)
    public void callApi() {
        String url = "https://teachnest-backend.onrender.com/"; // Replace with your API URL
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("API Response: " + response);
    }
}