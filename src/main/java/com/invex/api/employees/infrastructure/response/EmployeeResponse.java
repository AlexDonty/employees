package com.invex.api.employees.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invex.api.employees.infrastructure.dto.Employee;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse extends Employee {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("systemDate")
    private OffsetDateTime systemDate;
    @JsonProperty("status")
    private Boolean status;
}
