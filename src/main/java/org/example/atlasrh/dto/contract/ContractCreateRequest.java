package org.example.atlasrh.dto.contract;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.example.atlasrh.enums.ContractType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContractCreateRequest(
        @NotNull(message = "{contract.contractType.required}")
        ContractType contractType,

        @NotNull(message = "{contract.contractStartDate.required}")
        LocalDate startDate,

        LocalDate endDate,

        @NotNull(message = "{contract.contractGrossSalary.required}")
        @DecimalMin(value = "0.0", inclusive = false, message = "{contract.contractGrossSalary.min}")
        BigDecimal grossSalary
) {}