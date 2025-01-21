package com.example.java_code_learn_spring_data_projection.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@Builder
public class DepartmentDto {
    @NotBlank
    private String name;
}
