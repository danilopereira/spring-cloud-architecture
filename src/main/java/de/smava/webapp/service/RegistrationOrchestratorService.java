package de.smava.webapp.service;

import de.smava.webapp.model.LoanDTO;
import de.smava.webapp.model.RegistrationRequestDTO;
import de.smava.webapp.model.RegistrationResponseVO;
import de.smava.webapp.model.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationOrchestratorService {
    private final UserService userService;
    private final PersonService personService;
    private final LoanService loanService;

    @Transactional
    public RegistrationResponseVO register(RegistrationRequestDTO registrationRequest) {
        log.info("Entering register: loan: {}", registrationRequest.getLoan());
        UserDTO userDTO = userService.create(registrationRequest.getUser());
        personService.create(registrationRequest.getPerson(), userDTO);
        LoanDTO loanDTO = loanService.create(registrationRequest.getLoan(), userDTO);
        return new RegistrationResponseVO(userDTO.getId(), loanDTO.getId(), loanDTO.getStatus());
    }
}
