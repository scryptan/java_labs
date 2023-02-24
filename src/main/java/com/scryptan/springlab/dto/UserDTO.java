package com.scryptan.springlab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty(message = "Email should be not empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should be not empty")
    private String password;
}
