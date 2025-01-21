package com.example.java_code_learn_spring_data_projection.controller;

import com.example.java_code_learn_spring_data_projection.dto.DepartmentDto;
import com.example.java_code_learn_spring_data_projection.model.Department;
import com.example.java_code_learn_spring_data_projection.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService service;

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getDepartment(id));
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDto dto) {
        return ResponseEntity.ok().body(service.createDepartment(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto dto) {
        return ResponseEntity.ok().body(service.updateDepartment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        service.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
