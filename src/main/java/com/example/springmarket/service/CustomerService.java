package com.example.springmarket.service;

import com.example.springmarket.repository.CustomerRepository;
import com.example.springmarket.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
@AllArgsConstructor
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll() {return customerRepository.findAll();}

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer getById(int id) {
        Customer customer = null;
        Optional<Customer> optional = customerRepository.findById(id);
        if(optional.isPresent()){
            customer = optional.get();
        }
        return customer;
    }

    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }
}
