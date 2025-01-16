package com.andres.springboot.springmvc.app.controllers;

import com.andres.springboot.springmvc.app.entities.Items;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andres.springboot.springmvc.app.entities.Customer;
import com.andres.springboot.springmvc.app.entities.Factus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/factus")
@SessionAttributes("factu")
@Slf4j
public class FactusController {

    @GetMapping()
    public String list(Model model) {
        model.addAttribute("title", "Listado de facturas generadas");
        model.addAttribute("factu", new Customer());
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
    public String form(@Valid Factus factu, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status) {

        log.info(factu.toString());
        return "form";
    }

}
