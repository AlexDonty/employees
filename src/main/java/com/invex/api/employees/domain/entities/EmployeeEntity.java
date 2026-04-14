package com.invex.api.employees.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String secondName;
    private String lastName;
    private String motherName;
    private Integer age;
    private String gender;
    private LocalDate birthDate;
    private String position;
    private OffsetDateTime systemDate;
    private Boolean status;
}
