package de.danilopereira.homework.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
