package com.example.springmarket.service;

import com.example.springmarket.repository.CustomerRepository;
import com.example.springmarket.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer() {

        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Customer customer = null;
        Optional<Customer> optional = customerRepository.findById(id);
        if(optional.isPresent()){
            customer = optional.get();
        }
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
