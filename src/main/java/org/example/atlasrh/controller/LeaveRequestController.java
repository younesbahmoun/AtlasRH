package org.example.atlasrh.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.atlasrh.dto.LeaveRequest.LeaveDecisionRequest;
import org.example.atlasrh.dto.LeaveRequest.LeaveRequestResponse;
import org.example.atlasrh.service.interfaces.LeaveRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

//    Get pending leave requests of a manager team
    @GetMapping("/pending")
    @ResponseStatus(HttpStatus.OK)
//    best bractice using Spring Security not RequestParam
    public List<LeaveRequestResponse> getPendingRequests(@RequestParam Long managerId) {
        return leaveRequestService.getPendingRequests(managerId);
    }

    // Approve a leave request
    @PatchMapping("/{requestId}/approve")
    public LeaveRequestResponse approveRequest(@RequestParam Long managerId, @PathVariable Long requestId, @Valid @RequestBody(required = false) LeaveDecisionRequest request) {
        return leaveRequestService.approveRequest(managerId, requestId, request);
    }
}
