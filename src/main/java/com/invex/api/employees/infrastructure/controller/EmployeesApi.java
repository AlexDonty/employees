package com.invex.api.employees.infrastructure.controller;

import com.invex.api.employees.infrastructure.dto.Employee;
import com.invex.api.employees.infrastructure.request.EmployeeRequest;
import com.invex.api.employees.infrastructure.response.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface EmployeesApi {

    @Operation(summary = "Create employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create employees") })
    @RequestMapping(value = "/employees",
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<EmployeeResponse> createEmployees( @Valid @RequestBody EmployeeRequest employeeRequest);

    @Operation(summary = "Delete employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employees deleted"),

            @ApiResponse(responseCode = "404", description = "Employee not found") })
    @RequestMapping(value = "/employees/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id);

    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List Employees", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))) })
    @RequestMapping(value = "/employees",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<EmployeeResponse>> getAllEmployees();


    @Operation(summary = "Get employees by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detail Employee", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))),

            @ApiResponse(responseCode = "404", description = "Employee not found") })
    @RequestMapping(value = "/employees/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<EmployeeResponse> getEmployeeById( @PathVariable("id") Long id);


    @Operation(summary = "Get employees by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List employees", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))) })
    @RequestMapping(value = "/employees/search",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<EmployeeResponse>> searchEmployees(@NotNull @Valid @RequestParam(value = "name", required = true) String name
    );

    @Operation(summary = "update employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "employees updated"),
            @ApiResponse(responseCode = "404", description = "employees not found") })
    @RequestMapping(value = "/employees/{id}",
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Void> updateEmployee(@PathVariable("id") Long id
            ,@Valid @RequestBody EmployeeRequest body
    );
}
