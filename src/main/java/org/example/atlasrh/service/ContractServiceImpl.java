package org.example.atlasrh.service;

import lombok.RequiredArgsConstructor;
import org.example.atlasrh.dto.contract.ContractCreateRequest;
import org.example.atlasrh.dto.contract.ContractResponse;
import org.example.atlasrh.entity.Contract;
import org.example.atlasrh.entity.Employee;
import org.example.atlasrh.exception.InvalidEmployeeDataException;
import org.example.atlasrh.exception.ResourceNotFoundException;
import org.example.atlasrh.mapper.ContractMapper;
import org.example.atlasrh.repository.ContractRepository;
import org.example.atlasrh.repository.EmployeeRepository;
import org.example.atlasrh.service.interfaces.ContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final EmployeeRepository employeeRepository;
    private final ContractMapper contractMapper;

    @Override
    @Transactional
    public ContractResponse createContract(Long employeeId, ContractCreateRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (contractRepository.existsByEmployeeIdAndIsActiveTrue(employeeId)) {
            throw new InvalidEmployeeDataException("Employee already has an active contract");
        }

        if (request.endDate() != null && request.endDate().isBefore(request.startDate())) {
            throw new InvalidEmployeeDataException("End date cannot be before start date");
        }

        Contract contract = contractMapper.toEntity(request, employee);
        Contract savedContract = contractRepository.save(contract);

        return contractMapper.toResponse(savedContract);
    }

    @Override
    @Transactional
    public ContractResponse terminateContract(Long employeeId, Long contractId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        if (!contract.getEmployee().getId().equals(employeeId)) {
            throw new InvalidEmployeeDataException("Contract with id " + contractId + " does not belong to employee " + employeeId);
        }

        if (Boolean.FALSE.equals(contract.getIsActive())) {
            throw new InvalidEmployeeDataException("Contract is already terminated");
        }

        contract.setIsActive(false);
        contract.setEndDate(LocalDate.now());

        Contract updatedContract = contractRepository.save(contract);
        return contractMapper.toResponse(updatedContract);
    }
}