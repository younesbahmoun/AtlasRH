package org.example.atlasrh.service.interfaces;

import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeePatchRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;
import org.example.atlasrh.dto.employee.EmployeeUpdateRequest;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse add(Long departmentId, EmployeeCreateRequest request);
    EmployeeResponse getEmployeeByDepartmentId(Long departmentId, Long employeeId);
    List<EmployeeResponse> getEmployeesByDepartment(Long departmentId);
    EmployeeResponse updateEmployee(Long employeeId, EmployeeUpdateRequest request);

    EmployeeResponse patchEmployee(Long employeeId, EmployeePatchRequest request);

}
