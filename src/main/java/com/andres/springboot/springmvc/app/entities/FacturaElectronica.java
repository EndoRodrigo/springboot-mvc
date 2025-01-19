package com.andres.springboot.springmvc.app.entities;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class FacturaElectronica {
    private String qr;
    private double taxAmount;
    private double total;
    private String number;
    private String observation;
    private double grossValue;
    private String validated;
    private String cufe;
    private String status;
    private boolean sendEmail;
    private String reference_code;
    private String qrImage;
    private Long id;
    private String paymentDueDate;
    private double taxableAmount;
    private double discountRate;
    private String createdAt;
    private double discount;

    // Un mapa para capturar campos adicionales
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        additionalProperties.put(name, value);
    }

    // Getters y setters
}
