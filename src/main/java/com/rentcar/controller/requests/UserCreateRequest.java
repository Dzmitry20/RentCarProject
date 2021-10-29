package com.rentcar.controller.requests;

import com.rentcar.domain.status.Gender;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiOperation("Class for updating user entity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "Enter name")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "Enter name")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String surname;

    private Gender gender;

    private Long phone;

    private String passportSeries;

    private Integer passportNumber;

    private String email;

    private Integer driverLicenseNumber;

    private String login;

    private String password;
}
