package org.example.atlasrh.service.interfaces;

import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeePatchRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;
import org.example.atlasrh.dto.employee.EmployeeUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeCreateRequest request);
    EmployeeResponse getEmployeeById(Long employeeId);
    EmployeeResponse updateEmployee(Long employeeId, EmployeeUpdateRequest request);
    EmployeeResponse patchEmployee(Long employeeId, EmployeePatchRequest request);
    Page<EmployeeResponse> getAllEmployees(String fullName, Long departmentId, Pageable pageable);
    Page<EmployeeResponse> findByManagerId(Long managerId, Pageable pageable);
}
