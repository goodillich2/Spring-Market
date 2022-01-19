package com.example.springmarket.service;

import com.example.springmarket.model.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getAllCustomer();
    public void saveCustomer(Customer customer);
    public Customer getCustomer(int id);
    public void deleteCustomer(int id);
}
