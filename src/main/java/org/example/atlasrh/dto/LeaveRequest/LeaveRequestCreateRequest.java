package org.example.atlasrh.dto.LeaveRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LeaveRequestCreateRequest(

        @NotNull(message = "{leaveRequest.leaveTypeId.required}")
        Long leaveTypeId,

        @NotNull(message = "{leaveRequest.startDate.required}")
        LocalDate startDate,

        @NotNull(message = "{leaveRequest.endDate.required}")
        LocalDate endDate,

        @NotBlank(message = "{leaveRequest.reason.required}")
        @Size(max = 1000, message = "{leaveRequest.reason.size}")
        String reason

) {}
