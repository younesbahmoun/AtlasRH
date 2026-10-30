package org.example.atlasrh.repository;

import org.example.atlasrh.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmployeeNumber(String employeeNumber);
    boolean existsByEmail(String email);
//    Optional<Employee> findByIdAndDepartmentId(Long employeeId, Long departmentId);
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);
    boolean existsByEmailAndIdNot(String email, Long employeeId);
    boolean existsByEmployeeNumberAndIdNot(String employeeNumber, Long employeeId);

    // Search by name + pagination
    @Query("""
        SELECT e FROM Employee e
        WHERE LOWER(CONCAT(e.firstName, ' ', e.lastName))
        LIKE LOWER(CONCAT('%', :fullName, '%'))
    """)
    Page<Employee> findByFullNameContainingIgnoreCase(@Param("fullName") String fullName, Pageable pageable);

    // Search by name AND department + pagination
    @Query("""
        SELECT e FROM Employee e
        WHERE LOWER(CONCAT(e.firstName, ' ', e.lastName))
        LIKE LOWER(CONCAT('%', :fullName, '%'))
        AND e.department.id = :departmentId
    """)
    Page<Employee> findByFullNameContainingIgnoreCaseAndDepartmentId(@Param("fullName") String fullName, @Param("departmentId") Long departmentId, Pageable pageable);

}
