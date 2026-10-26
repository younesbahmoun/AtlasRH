package org.example.atlasrh.repository;

import org.example.atlasrh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmployeeNumber(String employeeNumber);
    boolean existsByEmail(String email);
    Optional<Employee> findByIdAndDepartmentId(Long employeeId, Long departmentId);
    List<Employee> findByDepartmentId(Long departmentId);
    boolean existsByEmailAndIdNot(String email, Long employeeId);
    boolean existsByEmployeeNumberAndIdNot(String employeeNumber, Long employeeId);
}
