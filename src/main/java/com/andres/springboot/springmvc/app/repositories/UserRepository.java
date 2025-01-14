package com.andres.springboot.springmvc.app.repositories;

import com.andres.springboot.springmvc.app.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Customer, Long> {

}
