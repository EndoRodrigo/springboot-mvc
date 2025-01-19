package com.andres.springboot.springmvc.app.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data 
public class Factus {

    private int numbering_range_id;
    private String reference_code;
    private String observation;
    private String payment_method_code = "10";
    private Customer customer;
    private List<Items> items;

}
