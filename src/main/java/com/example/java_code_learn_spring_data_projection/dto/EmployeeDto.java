package com.example.java_code_learn_spring_data_projection.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@Builder
public class EmployeeDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String position;

    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    private Double salary;

    @Min(value = 1, message = "department id must be greater than 0")
    private Long departmentId;
}
