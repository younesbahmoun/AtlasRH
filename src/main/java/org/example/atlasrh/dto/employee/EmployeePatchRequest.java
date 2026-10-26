package org.example.atlasrh.dto.employee;

import jakarta.validation.constraints.*;
import org.example.atlasrh.enums.ContractType;

import java.time.LocalDate;

public class EmployeePatchRequest {

    @Size(max = 50, message = "{employee.number.size}")
    private String employeeNumber;

    @Size(max = 100, message = "{employee.firstName.size}")
    private String firstName;

    @Size(max = 100, message = "{employee.lastName.size}")
    private String lastName;

    @Email(message = "{employee.email.invalid}")
    @Size(max = 150, message = "{employee.email.size}")
    private String email;

    @Size(max = 100, message = "{employee.jobTitle.size}")
    private String jobTitle;

    @PastOrPresent(message = "{employee.hireDate.invalid}")
    private LocalDate hireDate;

    private ContractType contractType;

    private Long departmentId;

    private Long managerId;

    public EmployeePatchRequest() {
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}