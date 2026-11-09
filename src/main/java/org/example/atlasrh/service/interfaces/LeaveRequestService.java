package org.example.atlasrh.service.interfaces;


import org.example.atlasrh.dto.LeaveRequest.LeaveDecisionRequest;
import org.example.atlasrh.dto.LeaveRequest.LeaveRequestResponse;

import java.util.List;

public interface LeaveRequestService {

    List<LeaveRequestResponse> getPendingRequests(Long managerId);
}

