package de.smava.homework.customer.model;

import lombok.Data;

@Data
public class CustomerRequestDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
