package org.example.atlasrh.service;

import lombok.RequiredArgsConstructor;
import org.example.atlasrh.dto.LeaveRequest.LeaveDecisionRequest;
import org.example.atlasrh.dto.LeaveRequest.LeaveRequestResponse;
import org.example.atlasrh.entity.LeaveRequest;
import org.example.atlasrh.enums.LeaveStatus;
import org.example.atlasrh.exception.ResourceNotFoundException;
import org.example.atlasrh.mapper.LeaveRequestMapper;
import org.example.atlasrh.repository.EmployeeRepository;
import org.example.atlasrh.repository.LeaveRequestRepository;
import org.example.atlasrh.service.interfaces.LeaveRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveRequestMapper leaveRequestMapper;

    // Get pending leave requests of a manager team
    @Override
    @Transactional(readOnly = true)
    public List<LeaveRequestResponse> getPendingRequests(Long managerId) {

        if (!employeeRepository.existsById(managerId)) {
            throw new ResourceNotFoundException("Manager not found");
        }

        return leaveRequestRepository.findByEmployeeManagerIdAndStatus(managerId, LeaveStatus.EN_ATTENTE)
                .stream()
                .map(leaveRequestMapper::toResponse)
                .toList();
    }

    // Approve a leave request
    @Override
    public LeaveRequestResponse approveRequest(Long managerId, Long requestId, LeaveDecisionRequest request) {

        LeaveRequest leaveRequest = getPendingRequest(managerId, requestId);

        leaveRequest.setStatus(LeaveStatus.APPROUVEE);
        leaveRequest.setComment(request != null ? request.comment() : null);
        leaveRequest.setDecisionDate(LocalDateTime.now());

        return leaveRequestMapper.toResponse(leaveRequestRepository.save(leaveRequest));
    }

    // Verify that the request belongs to the manager team
    // and is still pending
    private LeaveRequest getPendingRequest(Long managerId, Long requestId) {

        if (!employeeRepository.existsById(managerId)) {
            throw new ResourceNotFoundException("Manager not found");
        }

        LeaveRequest leaveRequest = leaveRequestRepository.findByIdAndEmployeeManagerId(requestId, managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found in your team"));

        if (leaveRequest.getStatus() != LeaveStatus.EN_ATTENTE) {
            throw new IllegalStateException("Only pending leave requests can be processed");
        }

        return leaveRequest;
    }
}