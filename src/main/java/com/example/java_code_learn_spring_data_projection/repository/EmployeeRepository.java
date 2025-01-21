package com.example.java_code_learn_spring_data_projection.repository;

import com.example.java_code_learn_spring_data_projection.model.Employee;
import com.example.java_code_learn_spring_data_projection.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @NativeQuery("SELECT e.first_name || ' ' || e.last_name as fullName, e.position as position, " +
            "d.name as departmentName FROM employee e JOIN department d WHERE e.id = :id")
    EmployeeProjection getEmployeeProjectionById(@Param("id") Long id);

    Optional<Employee> findByFirstNameAndLastNameAndPosition(String firstName, String lastName,
                                                             String position);
}