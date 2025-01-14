package com.andres.springboot.springmvc.app.services;

import com.andres.springboot.springmvc.app.entities.Customer;
import com.andres.springboot.springmvc.app.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> findAll() {
        return (List<Customer>) this.repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Customer save(Customer user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
