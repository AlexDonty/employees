package com.invex.api.employees.application.service.Impl;

import com.invex.api.employees.application.exception.NotFountException;
import com.invex.api.employees.application.service.EmployeesService;
import com.invex.api.employees.application.utils.Utils;
import com.invex.api.employees.domain.entities.EmployeeEntity;
import com.invex.api.employees.domain.repository.EmployeeRepository;
import com.invex.api.employees.infrastructure.request.EmployeeRequest;
import com.invex.api.employees.infrastructure.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.invex.api.employees.application.utils.Utils.convertEntity;
import static com.invex.api.employees.application.utils.Utils.convertResponse;

@Service
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployees(EmployeeRequest employeeRequest) {

        final EmployeeEntity employeeEntity = convertEntity(employeeRequest);
        EmployeeEntity employeeEntityNew =  employeeRepository.save(employeeEntity);
        return convertResponse(employeeEntityNew);
    }

    @Override
    public void deleteEmployee(Long id) {
        final EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Employee not found with id: " + id));
        employeeRepository.delete(employeeEntity);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {

        final List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(Utils::convertResponse).toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        final EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Employee not found with id: " + id));
        return convertResponse(employeeEntity);
    }

    @Override
    public List<EmployeeResponse> searchEmployees(String name) {
        final List<EmployeeEntity> employeeEntity = employeeRepository.findByNameContaining(name);
        return employeeEntity.stream().map(Utils::convertResponse).toList();
    }

    @Override
    public void updateEmployee(Long id, EmployeeRequest body) {
        final EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Employee not found with id: " + id));

        EmployeeEntity employeeEntityNew =  convertEntity(body);
        employeeEntityNew.setId(employeeEntity.getId());
        employeeEntityNew.setStatus(employeeEntity.getStatus());
        employeeRepository.save(employeeEntity);
    }
}
