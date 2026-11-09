package org.example.atlasrh.dto.LeaveRequest;

import org.example.atlasrh.enums.LeaveStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LeaveRequestResponse(
        Long id,
        Long employeeId,
        String employeeFirstName,
        String employeeLastName,
        String leaveType,
        LocalDate startDate,
        LocalDate endDate,
        String reason,
        LeaveStatus status,
        String comment,
        LocalDateTime decisionDate
) {}
