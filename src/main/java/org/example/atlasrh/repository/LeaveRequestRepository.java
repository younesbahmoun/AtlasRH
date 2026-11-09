package org.example.atlasrh.repository;


import org.example.atlasrh.entity.LeaveRequest;
import org.example.atlasrh.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

//    existing.start_date <= new.end_date
//            AND
//    existing.end_date >= new.start_date
    boolean existsByEmployeeIdAndStatusNotAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long employeeId,
            LeaveStatus status,
            LocalDate endDate,
            LocalDate startDate
    );
}
