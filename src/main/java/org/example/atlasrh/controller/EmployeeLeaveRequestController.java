package org.example.atlasrh.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.atlasrh.dto.LeaveRequest.LeaveRequestCreateRequest;
import org.example.atlasrh.dto.LeaveRequest.LeaveRequestResponse;
import org.example.atlasrh.enums.LeaveStatus;
import org.example.atlasrh.service.interfaces.EmployeeLeaveRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee/leave-requests")
@RequiredArgsConstructor
public class EmployeeLeaveRequestController {

    private final EmployeeLeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<LeaveRequestResponse> createRequest(@RequestParam Long employeeId, @Valid @RequestBody LeaveRequestCreateRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(leaveRequestService.createRequest(employeeId, request));
    }
}