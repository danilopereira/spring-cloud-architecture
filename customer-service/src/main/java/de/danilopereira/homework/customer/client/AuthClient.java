package de.danilopereira.homework.customer.client;

import de.danilopereira.homework.customer.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("${auth-service.name}")
public interface AuthClient {

    @GetMapping("/api/users/{id}")
    Optional<UserDTO> findById(@PathVariable("id") String id);
}
