package org.example.atlasrh.service;

import org.example.atlasrh.dto.department.DepartmentCreateRequest;
import org.example.atlasrh.dto.department.DepartmentResponse;
import org.example.atlasrh.dto.department.DepartmentUpdateRequest;
import org.example.atlasrh.entity.Department;
import org.example.atlasrh.exception.DuplicateResourceException;
import org.example.atlasrh.exception.ResourceNotFoundException;
import org.example.atlasrh.mapper.DepartmentMapper;
import org.example.atlasrh.repository.DepartmentRepository;
import org.example.atlasrh.service.interfaces.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    @Transactional
    public DepartmentResponse create(DepartmentCreateRequest request) {

        if (departmentRepository.existsByNameIgnoreCase(request.name())) {
            throw new DuplicateResourceException("A department with this name already exists");
        }

        Department department = departmentMapper.toEntity(request);
        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with id: " + id)
                );
//        long employeeCount = departmentRepository.countEmployeesByDepartmentId(id);
//        if(employeeCount > 0) {
//            throw new DepartmentHasEmployeesException(id);
//        }
        departmentRepository.delete(department);
    }



    @Override
    @Transactional
    public DepartmentResponse update(Long id, DepartmentUpdateRequest request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        if (departmentRepository.existsByNameIgnoreCase(request.name()) && !department.getName().equalsIgnoreCase(request.name())) {
            throw new DuplicateResourceException("A department with this name already exists");
        }

        department.setName(request.name());
        department.setDescription(request.description());

        Department updatedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(updatedDepartment);
    }

}
