package org.example.atlasrh.service.interfaces;

import org.example.atlasrh.dto.contract.ContractCreateRequest;
import org.example.atlasrh.dto.contract.ContractResponse;

import java.util.List;

public interface ContractService {
    ContractResponse createContract(Long employeeId, ContractCreateRequest request);
    ContractResponse terminateContract(Long employeeId, Long contractId);
    List<ContractResponse> getEmployeeContractHistory(Long employeeId);
}