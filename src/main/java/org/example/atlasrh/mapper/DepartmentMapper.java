package org.example.atlasrh.mapper;

import org.example.atlasrh.dto.department.DepartmentCreateRequest;
import org.example.atlasrh.dto.department.DepartmentResponse;
import org.example.atlasrh.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentCreateRequest request) {
        Department department = new Department();
        department.setName(request.name());
        department.setDescription(request.description());

        return department;
    }

    public DepartmentResponse toResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }
}

