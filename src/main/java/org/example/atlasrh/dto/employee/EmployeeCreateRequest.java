package org.example.atlasrh.dto.employee;

import jakarta.validation.constraints.*;
import org.example.atlasrh.enums.ContractType;

import java.time.LocalDate;

public class EmployeeCreateRequest {

    @NotBlank(message = "{employee.number.required}")
    @Size(max = 50, message = "{employee.number.size}")
    private String employeeNumber;

    @NotBlank(message = "{employee.firstName.required}")
    @Size(max = 100, message = "{employee.firstName.size}")
    private String firstName;

    @NotBlank(message = "{employee.lastName.required}")
    @Size(max = 100, message = "{employee.lastName.size}")
    private String lastName;

    @Email(message = "{employee.email.invalid}")
    @NotBlank(message = "{employee.email.required}")
    @Size(max = 150, message = "{employee.email.size}")
    private String email;

    @NotBlank(message = "{employee.jobTitle.required}")
    @Size(max = 100, message = "{employee.jobTitle.size}")
    private String jobTitle;

    @NotNull(message = "{employee.hireDate.required}")
    @PastOrPresent(message = "{employee.hireDate.invalid}")
    private LocalDate hireDate;

    @NotNull(message = "{employee.contractType.required}")
    private ContractType contractType;

    private Long managerId;

    public EmployeeCreateRequest() {
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

}