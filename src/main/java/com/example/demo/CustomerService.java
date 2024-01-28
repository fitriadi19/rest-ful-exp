package com.example.demo;

import com.example.entity.CustomerModel;
import com.example.entity.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    public final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerModel getOne(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }
    public CustomerModel createCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }
    public CustomerModel updateCustomer(int id, CustomerModel updatedCustomer) {
        CustomerModel existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setLastName(updatedCustomer.getLastName());

        return customerRepository.save(existingCustomer);
    }
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
    public CustomerModel patchCustomer(int id, Map<String, Object> updates) {
        CustomerModel existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingCustomer.setName((String) value);
                    break;
                case "lastName":
                    existingCustomer.setLastName((String) value);
                    break;
            }
        });

        return customerRepository.save(existingCustomer);
    }
}


