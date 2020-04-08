package de.smava.homework.customer.controller;

import de.smava.homework.customer.model.CustomerDTO;
import de.smava.homework.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findByCustomerId(@PathVariable("id") String id){
        return ResponseEntity.ok(customerService.findByCustomerId(id));
    }
}
