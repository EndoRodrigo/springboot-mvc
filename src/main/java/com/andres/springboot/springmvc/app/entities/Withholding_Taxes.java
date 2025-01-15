package com.andres.springboot.springmvc.app.entities;

import lombok.Data;

@Data
public class Withholding_Taxes {
    private Integer code;
    private Integer withholding_tax_rate;
}
