package de.smava.homework.loanapplications.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CustomerDTO {
    private String id;
    private String firstName;
    private String lastName;

    public CustomerDTO(CustomerClientDTO customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
    }
}
