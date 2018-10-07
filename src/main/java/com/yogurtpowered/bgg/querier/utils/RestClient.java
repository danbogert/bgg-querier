package com.yogurtpowered.bgg.querier.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {

    private static final int RETRY_ATTEMPTS = 3;

    private final RestTemplate restTemplate;

    public RestClient() {
        this.restTemplate = new RestTemplate();
    }

    public <T> T getWithRetry(String uriString, Class<T> responseType) {
        return getWithRetry(uriString, responseType, 1);
    }

    private <T> T getWithRetry(String uriString, Class<T> responseType, int attemptCount) {
        ResponseEntity<T> response = restTemplate.getForEntity(uriString, responseType);

        if (response.getStatusCodeValue() == 200) {
            return response.getBody();
        }

        try {
            Thread.sleep(100 * attemptCount * attemptCount);
        } catch (InterruptedException e) {
        }

        if (attemptCount <= RETRY_ATTEMPTS) {
            System.out.println("Retrying...");
            return getWithRetry(uriString, responseType, ++attemptCount);
        }

        System.out.println("Failed after " + RETRY_ATTEMPTS + " attempts");
        return null;
    }
}
