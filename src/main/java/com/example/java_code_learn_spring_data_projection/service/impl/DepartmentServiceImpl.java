package com.example.java_code_learn_spring_data_projection.service.impl;

import com.example.java_code_learn_spring_data_projection.dto.DepartmentDto;
import com.example.java_code_learn_spring_data_projection.model.Department;
import com.example.java_code_learn_spring_data_projection.repository.DepartmentRepository;
import com.example.java_code_learn_spring_data_projection.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;
    private final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Override
    public Department createDepartment(DepartmentDto dto) {
        logger.info("Was invoked department create method in service");
        if (repository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Department already exist");
        } else {
            Department newDepartment = Department.builder()
                    .name(dto.getName()).build();
            return repository.save(newDepartment);
        }
    }

    @Override
    public Department updateDepartment(Long id, DepartmentDto dto) {
        logger.info("Was invoked department update method in service");
        Department department = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Department is not exist"));
        department.setName(dto.getName());
        return repository.save(department);
    }

    @Override
    public Department getDepartment(Long id) {
        logger.info("Was invoked get department method in service");
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Department is not exist"));
    }

    @Override
    public void deleteDepartment(Long id) {
        logger.info("Was invoked delete department method in service");
        repository.findById(id).orElseThrow(
                () -> new RuntimeException("Department is not exist"));
        repository.deleteById(id);
    }
}
