package org.example.atlasrh.mapper;

import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;
import org.example.atlasrh.entity.Department;
import org.example.atlasrh.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeCreateRequest request, Department department, Employee manager) {

            Employee employee = new Employee();
            employee.setEmployeeNumber(request.getEmployeeNumber());
            employee.setFirstName(request.getFirstName());
            employee.setLastName(request.getLastName());
            employee.setEmail(request.getEmail());
            employee.setJobTitle(request.getJobTitle());
            employee.setHireDate(request.getHireDate());
            employee.setContractType(request.getContractType());
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
        );
    }
}
