package org.example.atlasrh.service.interfaces;

import org.example.atlasrh.dto.department.DepartmentCreateRequest;
import org.example.atlasrh.dto.department.DepartmentResponse;
import org.example.atlasrh.dto.department.DepartmentUpdateRequest;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse create(DepartmentCreateRequest request);

    List<DepartmentResponse> findAll();

    void delete(Long id);

    DepartmentResponse update(Long id, DepartmentUpdateRequest request);
}
