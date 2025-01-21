package com.example.java_code_learn_spring_data_projection.controller;

import com.example.java_code_learn_spring_data_projection.dto.EmployeeDto;
import com.example.java_code_learn_spring_data_projection.model.Employee;
import com.example.java_code_learn_spring_data_projection.projection.EmployeeProjection;
import com.example.java_code_learn_spring_data_projection.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getEmployee(id));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto dto) {
        return ResponseEntity.ok().body(service.createEmployee(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto dto) {
        return ResponseEntity.ok().body(service.updateEmployee(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projection/{id}")
    public ResponseEntity<EmployeeProjection> getEmployeeProjection(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getEmployeeProjection(id));
    }
}
