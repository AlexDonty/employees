package com.invex.api.employees.domain.repository;

import com.invex.api.employees.domain.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findById(Long id);

    List<EmployeeEntity> findByNameContaining(String name);
}
