package com.example.demo;

import com.example.entity.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {"com.example.demo", "com.example.entity"})
@EnableJpaRepositories(basePackages = "com.example.entity")
@EntityScan({"com.example.demo", "com.example.entity"})
@RestController
@RequestMapping("/customers")
public class CustomerController {

    public static void main(String[] args) {
        SpringApplication.run(CustomerController.class, args);
    }

    @Autowired
    public CustomerService customerService;

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerModel> patchCustomer(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        CustomerModel updatedCustomer = customerService.patchCustomer(id, updates);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable int id, @RequestBody CustomerModel updatedCustomer) {
        CustomerModel customer = customerService.updateCustomer(id, updatedCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customer) {
        CustomerModel createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CustomerModel>> getAllCustomers() {
        List<CustomerModel> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getOne(@PathVariable int id) {
        CustomerModel customer = customerService.getOne(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}




