package com.example.java_code_learn_spring_data_projection.service.impl;

import com.example.java_code_learn_spring_data_projection.dto.EmployeeDto;
import com.example.java_code_learn_spring_data_projection.model.Department;
import com.example.java_code_learn_spring_data_projection.model.Employee;
import com.example.java_code_learn_spring_data_projection.projection.EmployeeProjection;
import com.example.java_code_learn_spring_data_projection.repository.DepartmentRepository;
import com.example.java_code_learn_spring_data_projection.repository.EmployeeRepository;
import com.example.java_code_learn_spring_data_projection.service.EmployeeService;
import com.example.java_code_learn_spring_data_projection.utils.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public Employee createEmployee(EmployeeDto dto) {
        logger.info("Was invoked employee create method in service");
        if (employeeRepository.findByFirstNameAndLastNameAndPosition(dto.getFirstName(),
                dto.getLastName(), dto.getPosition()).isPresent()) {
            throw new RuntimeException("Employee already exist");
        } else {
            return employeeRepository.save(EmployeeMapper.mapToEntity(dto,
                    isExistDept(dto.getDepartmentId())));
        }
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto dto) {
        logger.info("Was invoked employee update method in service");
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee is not exist"));
        Employee updateEmployee = EmployeeMapper.mapToEntity(dto,
                isExistDept(dto.getDepartmentId()));
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setPosition(updateEmployee.getPosition());
        employee.setSalary(updateEmployee.getSalary());
        employee.setDepartment(updateEmployee.getDepartment());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        logger.info("Was invoked get employee method in service");
        return employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee is not exist"));
    }

    @Override
    public void deleteEmployee(Long id) {
        logger.info("Was invoked delete employee method in service");
        employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee is not exist"));
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeProjection getEmployeeProjection(Long id) {
        logger.info("Was invoked get employee projection method in service");
        employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee is not exist"));
        return employeeRepository.getEmployeeProjectionById(id);
    }

    private Department isExistDept(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department is not exist"));
    }
}
