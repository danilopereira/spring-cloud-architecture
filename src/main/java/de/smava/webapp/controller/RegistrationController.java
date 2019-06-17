package de.smava.webapp.controller;

import de.smava.webapp.model.RegistrationRequestDTO;
import de.smava.webapp.model.RegistrationResponseVO;
import de.smava.webapp.service.RegistrationOrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationOrchestratorService registrationOrchestratorService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseVO> register(
            @RequestBody @Valid RegistrationRequestDTO registrationRequest) {
        return ResponseEntity.ok().body(registrationOrchestratorService.register(registrationRequest));
    }
}
