package com.example.java_code_learn_spring_data_projection.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class DepartmentDto {
    @NotBlank
    private String name;
}
