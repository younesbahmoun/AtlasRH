package org.example.atlasrh.dto.LeaveRequest;

import jakarta.validation.constraints.Size;

public record LeaveDecisionRequest(

        @Size(max = 1000, message = "{leaveRequest.comment.size}")
        String comment

) {}