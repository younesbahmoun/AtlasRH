package org.example.atlasrh.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.atlasrh.dto.LeaveRequest.LeaveRequestCreateRequest;
import org.example.atlasrh.dto.LeaveRequest.LeaveRequestResponse;
import org.example.atlasrh.entity.Employee;
import org.example.atlasrh.entity.LeaveRequest;
import org.example.atlasrh.entity.LeaveType;
import org.example.atlasrh.enums.LeaveStatus;
import org.example.atlasrh.exception.BusinessException;
import org.example.atlasrh.exception.ResourceNotFoundException;
import org.example.atlasrh.mapper.LeaveRequestMapper;
import org.example.atlasrh.repository.EmployeeRepository;
import org.example.atlasrh.repository.LeaveRequestRepository;
import org.example.atlasrh.repository.LeaveTypeRepository;
import org.example.atlasrh.service.interfaces.EmployeeLeaveRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeLeaveRequestServiceImpl implements EmployeeLeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveRequestMapper leaveRequestMapper;

    @Override
    public LeaveRequestResponse createRequest(Long employeeId, LeaveRequestCreateRequest request) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        LeaveType leaveType = leaveTypeRepository.findById(request.leaveTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Leave type not found"));

        if (request.endDate().isBefore(request.startDate())) {
            throw new BusinessException("End date cannot be before start date");
        }

        boolean hasOverlap = leaveRequestRepository
                .existsByEmployeeIdAndStatusNotAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        employeeId,
                        LeaveStatus.ANNULEE,
                        request.endDate(),
                        request.startDate()
                );

        if (hasOverlap) {
            throw new BusinessException("You already have a leave request during this period");
        }

        LeaveRequest leaveRequest = leaveRequestMapper.toEntity(request, employee, leaveType);

        return leaveRequestMapper.toResponse(leaveRequestRepository.save(leaveRequest));
    }

    @Override
    public List<LeaveRequestResponse> getMyRequests(Long employeeId, LeaveStatus status) {

        List<LeaveRequest> requests;

        if (status == null) {
            requests = leaveRequestRepository.findByEmployeeId(employeeId);
        } else {
            requests = leaveRequestRepository.findByEmployeeIdAndStatus(employeeId, status);
        }

        return requests.stream()
                .map(leaveRequestMapper::toResponse)
                .toList();
    }

    @Override
    public LeaveRequestResponse cancelRequest(Long employeeId, Long requestId) {

        LeaveRequest leaveRequest = leaveRequestRepository.findByIdAndEmployeeId(requestId, employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));

        if (leaveRequest.getStatus() != LeaveStatus.EN_ATTENTE) {
            throw new BusinessException("Only pending leave requests can be cancelled");
        }

        leaveRequest.setStatus(LeaveStatus.ANNULEE);

        return leaveRequestMapper.toResponse(leaveRequestRepository.save(leaveRequest));
    }
}