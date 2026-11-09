package org.example.atlasrh.repository;


import org.example.atlasrh.entity.LeaveRequest;
import org.example.atlasrh.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // Find pending leave requests of a manager team
    List<LeaveRequest> findByEmployeeManagerIdAndStatus(
            Long managerId,
            LeaveStatus status
    );

    Optional<LeaveRequest> findByIdAndEmployeeManagerId(
            Long requestId,
            Long managerId
    );
}
