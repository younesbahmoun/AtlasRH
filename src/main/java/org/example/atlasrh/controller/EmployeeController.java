package org.example.atlasrh.controller;

import jakarta.validation.Valid;
import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeePatchRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;
import org.example.atlasrh.dto.employee.EmployeeUpdateRequest;
import org.example.atlasrh.service.interfaces.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse getEmployeeByDepartmentId(@PathVariable("id") Long departmentId, @PathVariable("employeeId") Long employeeId) {
        return employeeService.getEmployeeByDepartmentId(departmentId, employeeId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> getEmployeesByDepartment(@PathVariable("id") Long departmentId) {
        return employeeService.getEmployeesByDepartment(departmentId);
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse updateEmployee(@PathVariable("id") Long departmentId, @PathVariable("employeeId") Long employeeId, @Valid @RequestBody EmployeeUpdateRequest request) {
        return employeeService.updateEmployee(employeeId, request);
    }


    @PatchMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse patchEmployee(@PathVariable Long employeeId, @Valid @RequestBody EmployeePatchRequest request) {
        return employeeService.patchEmployee(employeeId, request);
    }


}
