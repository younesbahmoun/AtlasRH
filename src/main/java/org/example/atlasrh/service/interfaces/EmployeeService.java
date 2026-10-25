package org.example.atlasrh.service.interfaces;

import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse add(Long departmentId, EmployeeCreateRequest request);
    EmployeeResponse getEmployeeByDepartmentId(Long departmentId, Long employeeId);
    List<EmployeeResponse> getEmployeesByDepartment(Long departmentId);
}
