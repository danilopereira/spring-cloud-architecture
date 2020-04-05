package de.smava.homework.loanapplications.model;

import lombok.Data;

@Data
public class CustomerClientDTO {
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
