package com.andres.springboot.springmvc.app.service;

import com.andres.springboot.springmvc.app.entities.FacturaElectronica;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class FactusService {

    private String FACTURA = "";
    private final RestClient restClient;


    public FactusService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("https://api-sandbox.factus.com.co").build();
    }

    public String postDataWithToken(String token, Object requestBody) throws JsonProcessingException {
        // Realizamos la solicitud HTTP
        ResponseEntity<String> response = restClient.post()
                .uri("/v1/bills/validate")
                .header("Authorization", "Bearer " + token)
                .contentType(APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .toEntity(String.class);

        log.info("Response status code: {}", response.getStatusCode());

        JsonNode jsonNode = null;
        String facturaNumber = "";

        if (response.getBody() != null) {
            log.info("Response body: {}", response.getBody());
            ObjectMapper objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readTree(response.getBody());
            FACTURA = String.valueOf(jsonNode.get("data").get("bill").get("number"));
            log.info("Numero de factura: {}", jsonNode.get("data").get("bill").get("number"));

        } else {
            log.info("Response body is empty");
        }

        return facturaNumber;
    }

    public FacturaElectronica getFactura(String token) throws JsonProcessingException {
        if (FACTURA != null && FACTURA.length() > 0) {
            String sinComillas = FACTURA.replace("\"", "");
            ResponseEntity<String> response = restClient.get()
                    .uri("/v1/bills/show/{sinComillas}", sinComillas)
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .toEntity(String.class);

            log.info("Response status code: {}", response.getStatusCode());

            JsonNode jsonNode = null;
            if (response.getBody() != null) {
                log.info("Response body: {}", response.getBody());
                ObjectMapper objectMapper = new ObjectMapper();
                jsonNode = objectMapper.readTree(response.getBody());

                if (jsonNode.has("data")) {
                    JsonNode billNode = jsonNode.get("data").get("bill");
                    FacturaElectronica bill = objectMapper.treeToValue(billNode, FacturaElectronica.class);
                    return bill;
                } else {
                    log.info("No 'bill' field found in the response");
                    return null;
                }
            } else {
                log.info("Response body is empty");
                return null;
            }
        }
        return null;
    }


}
