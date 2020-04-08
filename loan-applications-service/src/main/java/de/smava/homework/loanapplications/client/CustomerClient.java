package de.smava.homework.loanapplications.client;

import de.smava.homework.loanapplications.model.CustomerClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient("${customer-service.name}")
public interface CustomerClient {

    @GetMapping("/api/customers/{id}")
    Optional<CustomerClientDTO> findById(@PathVariable String id);
}
