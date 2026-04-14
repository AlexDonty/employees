package com.invex.api.employees.application.service;

import com.invex.api.employees.infrastructure.request.EmployeeRequest;
import com.invex.api.employees.infrastructure.response.EmployeeResponse;

import java.util.List;

public interface EmployeesService {

    EmployeeResponse createEmployees(EmployeeRequest employeeRequest);

    void deleteEmployee(Long id);

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeById(Long id);

    List<EmployeeResponse> searchEmployees(String name);

    void updateEmployee(Long id, EmployeeRequest body);
}
