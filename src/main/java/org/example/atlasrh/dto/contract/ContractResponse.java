package org.example.atlasrh.dto.contract;

import org.example.atlasrh.enums.ContractType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContractResponse(
        Long id,
        ContractType contractType,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal grossSalary,
        Boolean isActive,
        Long employeeId
) {}