package com.invex.api.employees.application.utils;

import com.invex.api.employees.domain.entities.EmployeeEntity;
import com.invex.api.employees.infrastructure.request.EmployeeRequest;
import com.invex.api.employees.infrastructure.response.EmployeeResponse;

import java.time.OffsetDateTime;

public class Utils {

    public static  EmployeeEntity convertEntity(EmployeeRequest employeeRequest) {
        return EmployeeEntity.builder()
                .name(employeeRequest.getName())
                .secondName(employeeRequest.getSecondName())
                .lastName(employeeRequest.getLastName())
                .motherName(employeeRequest.getMotherName())
                .age(employeeRequest.getAge())
                .gender(employeeRequest.getGender())
                .birthDate(employeeRequest.getBirthDate())
                .position(employeeRequest.getPosition())
                .status(true)
                .systemDate(OffsetDateTime.now())
                .build();
    }

    public static EmployeeResponse convertResponse(EmployeeEntity employee) {

        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setSecondName(employee.getSecondName());
        response.setLastName(employee.getLastName());
        response.setMotherName(employee.getMotherName());
        response.setAge(employee.getAge());
        response.setGender(employee.getGender());
        response.setBirthDate(employee.getBirthDate());
        response.setPosition(employee.getPosition());
        response.setSystemDate(employee.getSystemDate());
        response.setStatus(employee.getStatus());

        return response;
    }
}
