package org.example.atlasrh.dto.employee;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.atlasrh.enums.ContractType;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeePatchRequest {

    @Size(max = 50, message = "{employee.number.size}")
    private String employeeNumber;

    @Size(max = 100, message = "{employee.firstName.size}")
    private String firstName;

    @Size(max = 100, message = "{employee.lastName.size}")
    private String lastName;

    @Email(message = "{employee.email.invalid}")
    @Size(max = 150, message = "{employee.email.size}")
    private String email;

    @Size(max = 100, message = "{employee.jobTitle.size}")
    private String jobTitle;

    @PastOrPresent(message = "{employee.hireDate.invalid}")
    private LocalDate hireDate;

    private ContractType contractType;

    @Positive(message = "{department.departmentId.positive}")
    private Long departmentId;

    @Positive(message = "{employee.managerId.positive}")
    private Long managerId;
}