package org.example.atlasrh.service.interfaces;

import org.example.atlasrh.dto.LeaveRequest.LeaveRequestCreateRequest;
import org.example.atlasrh.dto.LeaveRequest.LeaveRequestResponse;
import org.example.atlasrh.enums.LeaveStatus;

import java.util.List;

public interface EmployeeLeaveRequestService {

    LeaveRequestResponse createRequest(
            Long employeeId,
            LeaveRequestCreateRequest request
    );
}