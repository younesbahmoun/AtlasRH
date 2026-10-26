package org.example.atlasrh.dto.employee;

import jakarta.validation.constraints.*;
import org.example.atlasrh.enums.ContractType;

import java.time.LocalDate;

public record EmployeeCreateRequest (

    @NotBlank(message = "{employee.number.required}")
    @Size(max = 50, message = "{employee.number.size}")
    String employeeNumber,

    @NotBlank(message = "{employee.firstName.required}")
    @Size(max = 100, message = "{employee.firstName.size}")
    String firstName,

    @NotBlank(message = "{employee.lastName.required}")
    @Size(max = 100, message = "{employee.lastName.size}")
    String lastName,

    @Email(message = "{employee.email.invalid}")
    @NotBlank(message = "{employee.email.required}")
    @Size(max = 150, message = "{employee.email.size}")
    String email,

    @NotBlank(message = "{employee.jobTitle.required}")
    @Size(max = 100, message = "{employee.jobTitle.size}")
    String jobTitle,

    @NotNull(message = "{employee.hireDate.required}")
    @PastOrPresent(message = "{employee.hireDate.invalid}")
    LocalDate hireDate,

    @NotNull(message = "{employee.contractType.required}")
    ContractType contractType,

    @Positive(message = "{employee.managerId.positive}")
    Long managerId
){}