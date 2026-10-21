package org.example.atlasrh.service;

import jakarta.validation.Valid;
import org.example.atlasrh.dto.department.DepartmentCreateRequest;
import org.example.atlasrh.dto.department.DepartmentResponse;
import org.example.atlasrh.dto.department.DepartmentUpdateRequest;
import org.example.atlasrh.entity.Department;
import org.example.atlasrh.exception.DepartmentHasEmployeesException;
import org.example.atlasrh.exception.DuplicateResourceException;
import org.example.atlasrh.exception.ResourceNotFoundException;
import org.example.atlasrh.mapper.DepartmentMapper;
import org.example.atlasrh.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Transactional
    public DepartmentResponse create(DepartmentCreateRequest request) {

        if (departmentRepository.existsByNameIgnoreCase(request.name())) {
            throw new DuplicateResourceException("A department with this name already exists");
        }

        Department department = departmentMapper.toEntity(request);
        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    @Transactional(readOnly = true)
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with id: " + id)
                );
        departmentRepository.delete(department);
    }

}
