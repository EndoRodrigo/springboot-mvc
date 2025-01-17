package com.andres.springboot.springmvc.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class FactusService {
    private final RestClient restClient;


    public FactusService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("https://api-sandbox.factus.com.co").build();
    }

    public String postDataWithToken(String token, Object requestBody) {
        ResponseEntity<Void> response = restClient.post()
                .uri( "/v1/bills/validate")
                .header("Authorization", "Bearer " + token)
                .contentType(APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .toBodilessEntity();
        log.info("Response status code: {}", response.getStatusCode());
        log.info("Respose: -> "+response.toString());
        return "";
    }
}
