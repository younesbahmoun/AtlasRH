package org.example.atlasrh.repository;

import org.example.atlasrh.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByEmployeeIdOrderByStartDateDesc(Long employeeId);

    boolean existsByEmployeeIdAndIsActiveTrue(Long employeeId);
}