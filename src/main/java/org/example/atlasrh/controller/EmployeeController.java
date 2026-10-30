package org.example.atlasrh.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeePatchRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;
import org.example.atlasrh.dto.employee.EmployeeUpdateRequest;
import org.example.atlasrh.entity.Employee;
import org.example.atlasrh.service.interfaces.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(@Valid @RequestBody EmployeeCreateRequest request) {

        return employeeService.createEmployee(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse getEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    // US2 - 3: Endpoint global avec pagination, tri et filtres optionnels (?name=...&departmentId=...&page=0&size=10&sort=lastName,asc)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<EmployeeResponse> getAllEmployees(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) Long departmentId,
            @PageableDefault(size = 4, sort = "hireDate", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return employeeService.getAllEmployees(fullName, departmentId, pageable);
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse updateEmployee(@PathVariable("employeeId") Long employeeId, @Valid @RequestBody EmployeeUpdateRequest request) {
        return employeeService.updateEmployee(employeeId, request);
    }

    @PatchMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse patchEmployee(@PathVariable Long employeeId, @Valid @RequestBody EmployeePatchRequest request) {
        return employeeService.patchEmployee(employeeId, request);
    }

//    @GetMapping("/manager/{managerId}/employees")
    @GetMapping("/{managerId}/team")
    @ResponseStatus(HttpStatus.OK)
    public Page<EmployeeResponse> findByManagerId(@PathVariable Long managerId, Pageable pageable) {
        return employeeService.findByManagerId(managerId, pageable);
    }

}
