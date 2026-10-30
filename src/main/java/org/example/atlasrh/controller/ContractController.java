package org.example.atlasrh.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.atlasrh.dto.contract.ContractCreateRequest;
import org.example.atlasrh.dto.contract.ContractResponse;
import org.example.atlasrh.service.interfaces.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees/{employeeId}/contracts")
public class ContractController {

    private final ContractService contractService;

    // US3-1: Créer un contrat pour un employé
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContractResponse createContract(@PathVariable Long employeeId, @Valid @RequestBody ContractCreateRequest request) {
        return contractService.createContract(employeeId, request);
    }
}