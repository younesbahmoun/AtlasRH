package org.example.atlasrh.mapper;


import org.example.atlasrh.dto.LeaveRequest.LeaveRequestCreateRequest;
import org.example.atlasrh.dto.LeaveRequest.LeaveRequestResponse;
import org.example.atlasrh.entity.Employee;
import org.example.atlasrh.entity.LeaveRequest;
import org.example.atlasrh.entity.LeaveType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeaveRequestMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "employeeFirstName", source = "employee.firstName")
    @Mapping(target = "employeeLastName", source = "employee.lastName")
    @Mapping(target = "leaveType", source = "leaveType.name")
    LeaveRequestResponse toResponse(LeaveRequest leaveRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", source = "employee")
    @Mapping(target = "leaveType", source = "leaveType")
    @Mapping(target = "status", constant = "EN_ATTENTE")
    @Mapping(target = "comment", ignore = true)
    @Mapping(target = "decisionDate", ignore = true)
    LeaveRequest toEntity(LeaveRequestCreateRequest request, Employee employee, LeaveType leaveType);
}
