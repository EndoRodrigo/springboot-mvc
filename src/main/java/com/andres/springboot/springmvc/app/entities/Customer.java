package com.andres.springboot.springmvc.app.entities;

import lombok.Data;

@Data
public class Customer {
   private String identification_document_id;
   private String identification;
   private String names;
   private Integer legal_organization_id;
   private Integer tribute_id;
   private Integer municipality_id;

}
