package de.smava.homework.customer.controller;

import de.smava.homework.customer.model.CustomerDTO;
import de.smava.homework.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(customerService.findByCustomerId(id));
    }
}
