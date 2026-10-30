package org.example.atlasrh.mapper;

import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeePatchRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;
import org.example.atlasrh.dto.employee.EmployeeUpdateRequest;
import org.example.atlasrh.entity.Department;
import org.example.atlasrh.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.BeanMapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employeeNumber", source = "request.employeeNumber")
    @Mapping(target = "firstName", source = "request.firstName")
    @Mapping(target = "lastName", source = "request.lastName")
    @Mapping(target = "email", source = "request.email")
    @Mapping(target = "jobTitle", source = "request.jobTitle")
    @Mapping(target = "hireDate", source = "request.hireDate")
    @Mapping(target = "department", source = "department")
    @Mapping(target = "manager", source = "manager")
    Employee toEntity(EmployeeCreateRequest request, Department department, Employee manager);

    EmployeeResponse toResponse(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "manager", ignore = true)
    void updateEntity(EmployeeUpdateRequest request, @MappingTarget Employee employee);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "manager", ignore = true)
    void patchEntity(EmployeePatchRequest request, @MappingTarget Employee employee);
}