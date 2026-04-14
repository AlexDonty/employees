package com.invex.api.employees.infrastructure.controller.impl;

import com.invex.api.employees.application.service.EmployeesService;
import com.invex.api.employees.infrastructure.controller.EmployeesApi;
import com.invex.api.employees.infrastructure.request.EmployeeRequest;
import com.invex.api.employees.infrastructure.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeesApiImpl implements EmployeesApi {

    private final EmployeesService employeesService;

    @Override
    public ResponseEntity<EmployeeResponse> createEmployees(EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeesService.createEmployees(employeeRequest));
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Long id) {
        employeesService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeesService.getAllEmployees());
    }

    @Override
    public ResponseEntity<EmployeeResponse> getEmployeeById(Long id) {
        return ResponseEntity.ok(employeesService.getEmployeeById(id));
    }

    @Override
    public ResponseEntity<List<EmployeeResponse>> searchEmployees(String name) {
        return ResponseEntity.ok(employeesService.searchEmployees(name));
    }

    @Override
    public ResponseEntity<Void> updateEmployee(Long id, EmployeeRequest body) {
        employeesService.updateEmployee(id, body);
        return ResponseEntity.ok().build();
    }
}
