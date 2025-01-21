package com.example.java_code_learn_spring_data_projection.service;

import com.example.java_code_learn_spring_data_projection.dto.EmployeeDto;
import com.example.java_code_learn_spring_data_projection.model.Employee;
import com.example.java_code_learn_spring_data_projection.projection.EmployeeProjection;

public interface EmployeeService {
    Employee createEmployee(EmployeeDto dto);

    Employee updateEmployee(Long id, EmployeeDto dto);

    Employee getEmployee(Long id);

    void deleteEmployee(Long id);

    EmployeeProjection getEmployeeProjection(Long id);
}
