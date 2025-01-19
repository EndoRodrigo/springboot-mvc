package com.andres.springboot.springmvc.app.controllers;

import com.andres.springboot.springmvc.app.entities.FacturaElectronica;
import com.andres.springboot.springmvc.app.entities.Items;
import com.andres.springboot.springmvc.app.service.FactusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andres.springboot.springmvc.app.entities.Customer;
import com.andres.springboot.springmvc.app.entities.Factus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/factus")
@SessionAttributes("factu")
@Slf4j
public class FactusController {

    private final FactusService factusService;
    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5ZGYzYzIwZS05N2ZhLTQyNDUtYmNmZS1mNTg4MWVjZmNiMzgiLCJqdGkiOiIzOTFjNWM4YzJmYmZhMTA5Yzg1OTkwMmRlZTIwNjhlNjIyYzU4YzAwNjU5YzI2ZjgwOGY1YTM2ZjUxY2Y0MzA5NWM2MDJmMjZlZjNiNDY1MyIsImlhdCI6MTczNzMwNzgxOS45NTE2MDQsIm5iZiI6MTczNzMwNzgxOS45NTE2MDgsImV4cCI6MTczNzMxMTQxOS45Mzk5MDksInN1YiI6IjMiLCJzY29wZXMiOltdfQ.AW2pRe_V9QUiJ6eP-Ts1hj6wFUm8ZNAJ9AJdiOGnu1kO5js94s2LtPdzvF2U0JWPmvqtBZTY4CCh6memBLrGB_K_g5gLwJbQt3aFpJXn_5G0LiFE5U4m6a9RpsKXWiQBV3z5eKqu8H-NLLzp_m1MYi_2WoQGab4kBHs191rQjRxmR1D4H_nGJuBKd7YjxryJC7hWQ_Dygn-Kp4EH2tIK0xk6TgcqZrjW8gR-pW8-i9YXxIyVutiG0ZjDJHm7p8sHLsSqBXGXOJv8SOdtKJ__KaSw5gh7CfvtYtpn7-KnHe0EUtCxPx8GfFA9u9_PQaheI39r4JiNJ4e4-CPQUI0mN_MdBqXwVDwNImhYcYxIRmj2eU6KsK-MNuZGtF2-_drNw8LCofrsmAvU13d_dUF1g_WzKbr_uq1gI0FafEtE9DDAfSLyxEA0XkgdRiYtf_yCTwBnfam8D5fuOLfVgUuxAZBkOnYDJoazA80shGrTo-YCD2AThwuqlt6FU6NlPx0ke-mL8_41L94VnAE0twIAiU5DSkzROZ48a2PP7e2XESDKLnfqaVHNwXeezBNOXw5lZJ1IfcdsUfODGyBJnp6UpaKQF3Nl7tYbkcFREpVuEswLvFFSNfZSp-rX6Myja9WZaVQz4-1JabKT2gBhqIE2zfVp2ZuxJXEzg_gW9QOGok8";
    public FactusController(FactusService factusService) {
        this.factusService = factusService;
    }

    @GetMapping()
    public String list(Model model) throws JsonProcessingException {
        model.addAttribute("title", "Listado de facturas generadas");

        FacturaElectronica data =  factusService.getFactura(token);

        log.info("Listado de facturas generadas: " + data);
        if (data == null) {
            model.addAttribute("data", data);
        }else{
            model.addAttribute("data", data);

        }

        return "list";
    }

    @GetMapping("/form")
    public String listE(Model model) {
        model.addAttribute("title", "Listado de facturas generadas");
        Customer customer = new Customer();
        Factus factus = new Factus();
        List<Items> iten = new ArrayList<>();
        factus.setCustomer(customer);
        factus.setItems(iten);
        model.addAttribute("factu", factus);
        return "form";
    }


    @PostMapping("/form")
    public String form(@Valid Factus factu, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status) throws JsonProcessingException {
        log.info(factu.toString());
        factusService.postDataWithToken(token, factu);
        return "redirect:/factus";
    }

}
