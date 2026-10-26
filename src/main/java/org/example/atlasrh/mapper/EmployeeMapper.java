package org.example.atlasrh.mapper;

import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeePatchRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;
import org.example.atlasrh.dto.employee.EmployeeUpdateRequest;
import org.example.atlasrh.entity.Department;
import org.example.atlasrh.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeCreateRequest request, Department department, Employee manager) {

            Employee employee = new Employee();
            employee.setEmployeeNumber(request.employeeNumber());
            employee.setFirstName(request.firstName());
            employee.setLastName(request.lastName());
            employee.setEmail(request.email());
            employee.setJobTitle(request.jobTitle());
            employee.setHireDate(request.hireDate());
            employee.setContractType(request.contractType());
            employee.setDepartment(department);
            employee.setManager(manager);

            return employee;
    }

    public EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getEmployeeNumber(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getJobTitle(),
                employee.getHireDate().toString(),
                employee.getContractType()
//                employee.getManager().getId(),
//                employee.getManager().getFirstName() + " " + employee.getManager().getLastName()
        );
    }

    public void updateEntity(EmployeeUpdateRequest request, Employee employee) {
        employee.setEmployeeNumber(request.employeeNumber());
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employee.setJobTitle(request.jobTitle());
        employee.setHireDate(request.hireDate());
        employee.setContractType(request.contractType());
    }

    public void patchEntity(EmployeePatchRequest request, Employee employee) {
        if (request.getEmployeeNumber() != null) {
            employee.setEmployeeNumber(request.getEmployeeNumber());
        }

        if (request.getFirstName() != null) {
            employee.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            employee.setLastName(request.getLastName());
        }

        if (request.getEmail() != null) {
            employee.setEmail(request.getEmail());
        }

        if (request.getJobTitle() != null) {
            employee.setJobTitle(request.getJobTitle());
        }

        if (request.getHireDate() != null) {
            employee.setHireDate(request.getHireDate());
        }

        if (request.getContractType() != null) {
            employee.setContractType(request.getContractType());
        }
    }
}
