package com.andres.springboot.springmvc.app.entities;

import java.util.List;

import lombok.Data;

@Data
public class Items {
    private String code_reference;
    private String name;
    private Integer quantity;
    private float discount_rate;
    private float price;
    private String tax_rate;
    private Integer unit_measure_id;
    private Integer standard_code_id;
    private Integer is_excluded;
    private Integer tribute_id;
    private List<Withholding_Taxes> texes;
}
