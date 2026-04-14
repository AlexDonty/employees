package com.invex.api.employees.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

/**
 * EmployeeRequest
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

  @JsonProperty("name")
  private String name;
  @JsonProperty("secondName")
  private String secondName;
  @JsonProperty("lastName")
  private String lastName;
  @JsonProperty("motherName")
  private String motherName;
  @JsonProperty("age")
  private Integer age;
  @JsonProperty("gender")
  private String gender;
  @JsonProperty("birthDate")
  private LocalDate birthDate;
  @JsonProperty("position")
  private String position;
  @JsonProperty("status")
  private Boolean status;

}
