package de.smava.homework.customer.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String roles;
}
