package org.example.atlasrh.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.atlasrh.dto.employee.EmployeeCreateRequest;
import org.example.atlasrh.dto.employee.EmployeePatchRequest;
import org.example.atlasrh.dto.employee.EmployeeResponse;
import org.example.atlasrh.dto.employee.EmployeeUpdateRequest;
import org.example.atlasrh.entity.Department;
import org.example.atlasrh.entity.Employee;
import org.example.atlasrh.exception.DuplicateResourceException;
import org.example.atlasrh.exception.InvalidEmployeeDataException;
import org.example.atlasrh.exception.InvalidEmployeeManagerException;
import org.example.atlasrh.exception.ResourceNotFoundException;
import org.example.atlasrh.mapper.EmployeeMapper;
import org.example.atlasrh.repository.DepartmentRepository;
import org.example.atlasrh.repository.EmployeeRepository;
import org.example.atlasrh.service.interfaces.EmployeeService;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public EmployeeResponse add(@Param("id") Long departmentId, EmployeeCreateRequest request) {

        log.info("Incoming Manager ID from request: {}", request.managerId());

        if(employeeRepository.existsByEmployeeNumber(request.employeeNumber())) {
            throw new DuplicateResourceException("employee with id: " + request.employeeNumber() + " already exist");
        }

        if(employeeRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("email: " + request.email() + " already exist");
        }

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        Employee manager = null;
        if (request.managerId() != null) {
            manager = employeeRepository.findById(request.managerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        }

        Employee employee = employeeMapper.toEntity(request, department, manager);

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse getEmployeeByDepartmentId(Long departmentId, Long employeeId) {

        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        Employee employee = employeeRepository.findByIdAndDepartmentId(employeeId, departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found in department with id " + departmentId));

        return employeeMapper.toResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartment(Long departmentId) {

        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        return employeeRepository.findByDepartmentId(departmentId)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(Long employeeId, EmployeeUpdateRequest request) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (employeeRepository.existsByEmailAndIdNot(request.email(), employeeId)) {
            throw new DuplicateResourceException("Email already exists: " + request.email());
        }

        if (employeeRepository.existsByEmployeeNumberAndIdNot(request.employeeNumber(), employeeId)) {
            throw new DuplicateResourceException("Employee number already exists: " + request.employeeNumber());
        }

        Department department = departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.departmentId()));

        Employee manager = null;

        if (request.managerId() != null) {
            if (request.managerId().equals(employeeId)) {
                throw new InvalidEmployeeManagerException("An employee cannot be their own manager");
            }

            manager = employeeRepository.findById(request.managerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + request.managerId()));
        }

        employeeMapper.updateEntity(request, employee);

        employee.setDepartment(department);
        employee.setManager(manager);

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(updatedEmployee);
    }





    @Override
    @Transactional
    public EmployeeResponse patchEmployee(Long employeeId, EmployeePatchRequest request) {

        validateTextFields(request);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (request.getEmail() != null && !request.getEmail().equals(employee.getEmail()) && employeeRepository.existsByEmailAndIdNot(request.getEmail(), employeeId)) {
            throw new DuplicateResourceException("Email already exists: " + request.getEmail());
        }

        if (request.getEmployeeNumber() != null && !request.getEmployeeNumber().equals(employee.getEmployeeNumber()) && employeeRepository.existsByEmployeeNumberAndIdNot(request.getEmployeeNumber(), employeeId)) {
            throw new DuplicateResourceException("Employee number already exists: " + request.getEmployeeNumber());
        }

        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.getDepartmentId()));

            employee.setDepartment(department);
        }

        JsonNullable<Long> managerIdField = request.getManagerId();

        if (managerIdField != null && managerIdField.isPresent()) {
            Long managerId = managerIdField.orElse(null);

            if (managerId == null) {
                employee.setManager(null);
            } else {
                if (managerId.equals(employeeId)) {
                    throw new InvalidEmployeeManagerException("An employee cannot be their own manager");
                }

                Employee manager = employeeRepository.findById(managerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + managerId));

                employee.setManager(manager);
            }
        }


        employeeMapper.patchEntity(request, employee);

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(updatedEmployee);
    }

    private void validateTextFields(EmployeePatchRequest request) {
        if (request.getEmployeeNumber() != null && request.getEmployeeNumber().isBlank()) {
            throw new InvalidEmployeeDataException("Employee number cannot be blank");
        }

        if (request.getFirstName() != null && request.getFirstName().isBlank()) {
            throw new InvalidEmployeeDataException("First name cannot be blank");
        }

        if (request.getLastName() != null && request.getLastName().isBlank()) {
            throw new InvalidEmployeeDataException("Last name cannot be blank");
        }

        if (request.getEmail() != null && request.getEmail().isBlank()) {
            throw new InvalidEmployeeDataException("Email cannot be blank");
        }

        if (request.getJobTitle() != null && request.getJobTitle().isBlank()) {
            throw new InvalidEmployeeDataException("Job title cannot be blank");
        }
    }
}
