package de.smava.homework.customer.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
