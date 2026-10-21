package org.example.atlasrh.controller;

import jakarta.validation.Valid;
import org.example.atlasrh.dto.department.DepartmentCreateRequest;
import org.example.atlasrh.dto.department.DepartmentResponse;
import org.example.atlasrh.dto.department.DepartmentUpdateRequest;
import org.example.atlasrh.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse create(@Valid @RequestBody DepartmentCreateRequest request) {
        return departmentService.create(request);
    }

}
