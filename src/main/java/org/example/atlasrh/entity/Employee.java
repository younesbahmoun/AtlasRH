package org.example.atlasrh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.atlasrh.enums.ContractType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;

@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_number", nullable = false, unique = true, length = 50)
    private String employeeNumber;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "job_title", nullable = false, length = 100)
    private String jobTitle;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "contract_type", nullable = false, length = 10)
    private ContractType contractType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<Employee> subordinates = new ArrayList<>();
}