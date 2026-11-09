package org.example.atlasrh.dto.employee;

import org.example.atlasrh.entity.Employee;
import org.example.atlasrh.enums.ContractType;

import java.time.LocalDate;

public record EmployeeResponse (
        Long id,
        String employeeNumber,
        String firstName,
        String lastName,
        String email,
        String jobTitle,
        LocalDate hireDate
//        Long managerId,
//        String managerFullName
) {}