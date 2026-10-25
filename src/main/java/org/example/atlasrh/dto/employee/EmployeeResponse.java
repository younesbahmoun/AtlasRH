package org.example.atlasrh.dto.employee;

import org.example.atlasrh.enums.ContractType;

public record EmployeeResponse (
        Long id,
        String employeeNumber,
        String firstName,
        String lastName,
        String jobTitle,
        ContractType contractType
) {}