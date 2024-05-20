package com.demo.UserManagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {

    private Long id;

    @NotEmpty
    @Size(min = 3, message = "Name must be minimum 3 charactors !!")
    private String name;

    @Email(message = "Email must be valid.")
    @NotEmpty(message = "Email is required.")
    private String email;

}
