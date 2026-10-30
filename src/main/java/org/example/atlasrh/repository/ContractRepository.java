package org.example.atlasrh.repository;

import org.example.atlasrh.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    boolean existsByEmployeeIdAndIsActiveTrue(Long employeeId);
}