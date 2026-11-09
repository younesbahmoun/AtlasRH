package org.example.atlasrh.service.interfaces;


import org.example.atlasrh.dto.LeaveRequest.LeaveDecisionRequest;
import org.example.atlasrh.dto.LeaveRequest.LeaveRequestResponse;

import java.util.List;

public interface ManagerLeaveRequestService {

    List<LeaveRequestResponse> getPendingRequests(Long managerId);

    LeaveRequestResponse approveRequest(
            Long managerId,
            Long requestId,
            LeaveDecisionRequest request
    );

    LeaveRequestResponse rejectRequest(
            Long managerId,
            Long requestId,
            LeaveDecisionRequest request
    );
}

