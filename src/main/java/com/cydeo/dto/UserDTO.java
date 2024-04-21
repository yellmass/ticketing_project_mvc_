package com.cydeo.dto;

import com.cydeo.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    @Size(max = 15, min = 2)
    private String firstName;
    @NotBlank
    @Size(max = 15, min = 2)
    private String lastName;
    @NotBlank
    @Email
    private String userName;
    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String password;



    private boolean enabled;
    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String phone;
    @NotNull
    private RoleDTO role;
    @NotNull
    private Gender gender;


}
