package org.example.atlasrh.dto.employee;

import org.example.atlasrh.enums.ContractType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EmployeeUpdateRequest(

        @NotBlank(message = "Employee number is required")
        @Size(max = 50, message = "Employee number cannot exceed 50 characters")
        String employeeNumber,

        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name cannot exceed 100 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name cannot exceed 100 characters")
        String lastName,

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        @Size(max = 150, message = "Email cannot exceed 150 characters")
        String email,

        @NotBlank(message = "Job title is required")
        @Size(max = 100, message = "Job title cannot exceed 100 characters")
        String jobTitle,

        @NotNull(message = "Hire date is required")
        @PastOrPresent(message = "Hire date cannot be in the future")
        LocalDate hireDate,

        @NotNull(message = "Contract type is required")
        ContractType contractType,

        @NotNull(message = "Department ID is required")
        Long departmentId,

        Long managerId
) {}