package com.invex.api.employees.application.service.Impl;

import com.invex.api.employees.application.exception.NotFountException;
import com.invex.api.employees.domain.entities.EmployeeEntity;
import com.invex.api.employees.domain.repository.EmployeeRepository;
import com.invex.api.employees.infrastructure.request.EmployeeRequest;
import com.invex.api.employees.infrastructure.response.EmployeeResponse;
import com.invex.api.employees.application.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeesServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeesServiceImpl employeesService;

    @BeforeEach
    void setUp() {
        employeesService = new EmployeesServiceImpl(employeeRepository);
    }

    @Test
    void createEmployees_returnsResponse() {
        EmployeeRequest req = new EmployeeRequest();
        req.setName("Alice");

        EmployeeEntity entityFromReq = new EmployeeEntity();
        entityFromReq.setName("Alice");

        EmployeeEntity savedEntity = new EmployeeEntity();
        savedEntity.setId(1L);
        savedEntity.setName("Alice");

        EmployeeResponse expectedResponse = new EmployeeResponse();
        expectedResponse.setId(1L);
        expectedResponse.setName("Alice");

        try (MockedStatic<Utils> utils = mockStatic(Utils.class)) {
            utils.when(() -> Utils.convertEntity(req)).thenReturn(entityFromReq);
            when(employeeRepository.save(entityFromReq)).thenReturn(savedEntity);
            utils.when(() -> Utils.convertResponse(savedEntity)).thenReturn(expectedResponse);

            EmployeeResponse actual = employeesService.createEmployees(req);

            assertEquals(expectedResponse, actual);
            verify(employeeRepository).save(entityFromReq);
        }
    }

    @Test
    void deleteEmployee_deletesWhenFound() {
        EmployeeEntity found = new EmployeeEntity();
        found.setId(2L);

        when(employeeRepository.findById(2L)).thenReturn(Optional.of(found));

        employeesService.deleteEmployee(2L);

        verify(employeeRepository).delete(found);
    }

    @Test
    void deleteEmployee_throwsWhenNotFound() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFountException.class, () -> employeesService.deleteEmployee(99L));
    }

    @Test
    void getAllEmployees_returnsList() {
        EmployeeEntity e1 = new EmployeeEntity();
        e1.setId(1L);
        e1.setName("A");
        EmployeeEntity e2 = new EmployeeEntity();
        e2.setId(2L);
        e2.setName("B");

        EmployeeResponse r1 = new EmployeeResponse();
        r1.setId(1L);
        r1.setName("A");
        EmployeeResponse r2 = new EmployeeResponse();
        r2.setId(2L);
        r2.setName("B");

        when(employeeRepository.findAll()).thenReturn(List.of(e1, e2));

        try (MockedStatic<Utils> utils = mockStatic(Utils.class)) {
            utils.when(() -> Utils.convertResponse(e1)).thenReturn(r1);
            utils.when(() -> Utils.convertResponse(e2)).thenReturn(r2);

            List<EmployeeResponse> res = employeesService.getAllEmployees();

            assertEquals(2, res.size());
            assertTrue(res.contains(r1));
            assertTrue(res.contains(r2));
        }
    }

    @Test
    void getEmployeeById_returnsResponse() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(5L);
        e.setName("Charlie");

        EmployeeResponse resp = new EmployeeResponse();
        resp.setId(5L);
        resp.setName("Charlie");

        when(employeeRepository.findById(5L)).thenReturn(Optional.of(e));
        try (MockedStatic<Utils> utils = mockStatic(Utils.class)) {
            utils.when(() -> Utils.convertResponse(e)).thenReturn(resp);

            EmployeeResponse actual = employeesService.getEmployeeById(5L);

            assertEquals(resp, actual);
        }
    }

    @Test
    void searchEmployees_returnsList() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(7L);
        e.setName("Dave");

        EmployeeResponse resp = new EmployeeResponse();
        resp.setId(7L);
        resp.setName("Dave");

        when(employeeRepository.findByNameContaining("Dav")).thenReturn(List.of(e));
        try (MockedStatic<Utils> utils = mockStatic(Utils.class)) {
            utils.when(() -> Utils.convertResponse(e)).thenReturn(resp);

            List<EmployeeResponse> result = employeesService.searchEmployees("Dav");

            assertEquals(1, result.size());
            assertEquals(resp, result.get(0));
        }
    }

    @Test
    void updateEmployee_savesUpdatedEntity() {
        EmployeeEntity existing = new EmployeeEntity();
        existing.setId(10L);
        existing.setStatus(true);
        existing.setName("OldName");

        EmployeeRequest body = new EmployeeRequest();
        body.setName("NewName");

        EmployeeEntity converted = new EmployeeEntity();
        converted.setName("NewName");

        EmployeeEntity expectedToSave = new EmployeeEntity();
        expectedToSave.setId(10L);
        expectedToSave.setStatus(true);
        expectedToSave.setName("NewName");

        when(employeeRepository.findById(10L)).thenReturn(Optional.of(existing));

        try (MockedStatic<Utils> utils = mockStatic(Utils.class)) {
            utils.when(() -> Utils.convertEntity(body)).thenReturn(converted);
            employeesService.updateEmployee(10L, body);
            verify(employeeRepository).save(any(EmployeeEntity.class));
        }
    }
}
