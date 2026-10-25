package org.example.atlasrh.controller;

import jakarta.validation.Valid;
import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;
import org.example.atlasrh.service.interfaces.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments/{id}/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@PathVariable("id") Long departmentId, @Valid @RequestBody EmployeeCreateRequest request) {
        return employeeService.add(departmentId, request);
    }

}
