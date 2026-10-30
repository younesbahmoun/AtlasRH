package org.example.atlasrh.service.interfaces;

import org.example.atlasrh.dto.contract.ContractCreateRequest;
import org.example.atlasrh.dto.contract.ContractResponse;

public interface ContractService {
    ContractResponse createContract(Long employeeId, ContractCreateRequest request);
}