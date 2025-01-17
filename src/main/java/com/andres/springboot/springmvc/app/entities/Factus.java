package com.andres.springboot.springmvc.app.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;


@Data 
public class Factus {

    //id unico de la facturacion
    private int numbering_range_id;

    // numero unico de la factura
    private String reference_code;

    // este campo puede ir vacio
    private String observation;

    // fecha de incio de la facturacion
    private LocalDate start_date;

    // fecha de finalizacion de la facturacion
    private LocalDate end_date;

    // hora de finalizacion de la facturacion
    private LocalTime end_time;

    // informacion del cliente
    private Customer customer;

    // Informacion de los articulos
    private List<Items> items;
}
