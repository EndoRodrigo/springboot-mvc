package com.andres.springboot.springmvc.app.controllers;

import com.andres.springboot.springmvc.app.entities.Items;
import com.andres.springboot.springmvc.app.service.FactusService;
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

    private final FactusService factusService;
    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5ZGYzYzIwZS05N2ZhLTQyNDUtYmNmZS1mNTg4MWVjZmNiMzgiLCJqdGkiOiI1YTE5ZDE4N2Q2MDNlN2RjYWE5ZDRjOGJmY2E1ODM5Mjc3MGNkMjM0ZWIxMzc1OTY4OTI1NGQxNGVlY2IyZDE0NmRhOGI4ZGY0NWExNWUzOCIsImlhdCI6MTczNzA3MTU4Ny4xMTM3NywibmJmIjoxNzM3MDcxNTg3LjExMzc3MywiZXhwIjoxNzM3MDc1MTg3LjEwNTIwOSwic3ViIjoiMyIsInNjb3BlcyI6W119.DDRfQ2F2f5IB4HtAqcGBsFZysz_70icab3lDM_5ekoMNDiQxDFBBbIZVHmeQPU8hP74xD7JTiC28fAEd4jL7ls-Ip2tiOwMaE8UnVN1M4mO0jcLEBmlv-Y4YV58cLOxKMwzjtbHk6vSNc89TafZzJ1MYt0v5G_-9AMMUGTLhjG6BDVDOZM40zU6UUMT6PKpZQxF9GGJgeWxisVP_vgQ7vwEjB1Z2L0ykhRQrAXh4_tEUnammmiKGp4wHes-y9GjCNIPd6H0rYI-EM_Dvsf9ZCwGEUA61opKOEIrWQs8XwAN80mcyWrDzhr5XF-4EIb4s8ItHn9GE2n_VNy0uUOHjfYFD2EmA-Yk72yRb-o0WKidxKRSRRFdyJDAsk-cTIWh6BvBP4H8sDn4bdyPnxOVDzI7DA819s_o7k9cquCpJ3ZLtePjbE79h7q7LG9jZXY0FBJvqY-g00yH-oFAXhguABiljiylTyZbeHlE4VykklcTWhNDb2ukcLLvWDxiQoxm5e_wCMeNuTw91BSPqmsf5PgWkp-DMyOgUIpEwTkrKsIfZhgd7l5wbaKKY95wMRuNFFNUHAfNPGDzhxkF6LeqVNV4YpHKXr_jofQoKmM6zlpm2oeMOKrYffvfHAI2dABGJCsC9D25NJvOHaGMW8kaOdo-mTjP7GqWri3TyGtpqjys";
    public FactusController(FactusService factusService) {
        this.factusService = factusService;
    }

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
        factusService.postDataWithToken(token, factu);
        return "form";
    }

}
