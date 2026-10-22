package org.example.atlasrh.mapper;

import org.example.atlasrh.dto.department.DepartmentCreateRequest;
import org.example.atlasrh.dto.department.DepartmentResponse;
import org.example.atlasrh.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(target = "id", ignore = true)
    Department toEntity(DepartmentCreateRequest request);

//    @Mapping(target = "id", source = "id")
    DepartmentResponse toResponse(Department department);
}
