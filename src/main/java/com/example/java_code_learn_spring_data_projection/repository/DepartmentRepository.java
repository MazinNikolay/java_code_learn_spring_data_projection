package com.example.java_code_learn_spring_data_projection.repository;

import com.example.java_code_learn_spring_data_projection.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
