package org.example.atlasrh.mapper;

import org.example.atlasrh.dto.contract.ContractCreateRequest;
import org.example.atlasrh.dto.contract.ContractResponse;
import org.example.atlasrh.entity.Contract;
import org.example.atlasrh.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "employee", source = "employee")
    Contract toEntity(ContractCreateRequest request, Employee employee);

    @Mapping(target = "employeeId", source = "employee.id")
    ContractResponse toResponse(Contract contract);
}