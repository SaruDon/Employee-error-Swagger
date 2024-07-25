package com.fcrit.springbootwebtutorial.springbootwebtutorial.dto;

import com.fcrit.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private Long id;
    @NotBlank
    @NotEmpty
    @Size(min=3, max = 10, message = "No of char should be in range 3 to 10")
    @NotNull(message = "Required field name")
    private String name;

    @Email(message = "email should be a valid email")
    private String email;

    @NotBlank(message = "Role can not be null")
//    @Pattern(regexp = "^(ADMIN|USER)$")
    @EmployeeRoleValidation //custom validator
    private String role;

    @Positive
    @Max(value = 80, message = "age can not be greater than 80")
    @Min(value = 18, message = "age can not be less than 19")
    private Integer age;

    @NotNull
    @Positive
    @Digits(integer = 6,fraction = 2, message = "Salary can be in for of XXXXXX.YY")
    @DecimalMax(value ="100000.99" ,message = "Value must be less than 100000.99")
    @DecimalMin(value =".10", message = " Value must be greater than .10")
    private Double salary;


    @PastOrPresent(message = "Date of joining can not in future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;
}
