package org.example.atlasrh.service.interfaces;

import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse add(Long departmentId, EmployeeCreateRequest request);
}
