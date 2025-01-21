package com.example.java_code_learn_spring_data_projection.service;

import com.example.java_code_learn_spring_data_projection.dto.DepartmentDto;
import com.example.java_code_learn_spring_data_projection.model.Department;

public interface DepartmentService {
    Department createDepartment(DepartmentDto dto);

    Department updateDepartment(Long id, DepartmentDto dto);

    Department getDepartment(Long id);

    void deleteDepartment(Long id);
}
