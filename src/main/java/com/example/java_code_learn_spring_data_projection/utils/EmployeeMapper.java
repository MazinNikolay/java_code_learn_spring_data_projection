package com.example.java_code_learn_spring_data_projection.utils;

import com.example.java_code_learn_spring_data_projection.dto.EmployeeDto;
import com.example.java_code_learn_spring_data_projection.model.Department;
import com.example.java_code_learn_spring_data_projection.model.Employee;

public class EmployeeMapper {
    public static Employee mapToEntity(EmployeeDto dto, Department department) {
        return Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .position(dto.getPosition())
                .salary(dto.getSalary())
                .department(department)
                .build();
    }
}
