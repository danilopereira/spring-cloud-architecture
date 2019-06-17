package de.smava.webapp.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationRequestDTO {
    @Valid
    @NotNull
    private UserDTO user;
    @Valid
    @NotNull
    private PersonDTO person;
    @Valid
    @NotNull
    private LoanDTO loan;
}
