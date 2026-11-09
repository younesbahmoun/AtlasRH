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
}