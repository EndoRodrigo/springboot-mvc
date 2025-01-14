package com.andres.springboot.springmvc.app.services;

import com.andres.springboot.springmvc.app.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<Customer>  findAll();
    Optional<Customer> findById(Long id);
    Customer save(Customer user);
    void remove(Long id);
}
