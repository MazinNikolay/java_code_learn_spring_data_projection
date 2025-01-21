package com.example.java_code_learn_spring_data_projection.controller;

import com.example.java_code_learn_spring_data_projection.dto.EmployeeDto;
import com.example.java_code_learn_spring_data_projection.model.Department;
import com.example.java_code_learn_spring_data_projection.model.Employee;
import com.example.java_code_learn_spring_data_projection.projection.EmployeeProjection;
import com.example.java_code_learn_spring_data_projection.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private Department department;
    private EmployeeDto dto;
    private EmployeeProjection projection;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .id(1L)
                .name("dept name")
                .build();
        employee = Employee.builder()
                .id(1L)
                .firstName("employee first name")
                .lastName("employee last name")
                .position("position")
                .salary(40.0)
                .department(department)
                .build();
        dto = EmployeeDto.builder()
                .firstName("dto first name")
                .lastName("dto last name")
                .salary(45.0)
                .position("dto position")
                .departmentId(1l)
                .build();
    }

    @Test
    void getEmployee() throws Exception {
        Mockito.when(employeeService.getEmployee(Mockito.anyLong())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/app/employee/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value("employee first name"));
    }

    @Test
    void createEmployee() throws Exception {
        String json = objectMapper.writeValueAsString(dto);
        Mockito.when(employeeService.createEmployee(Mockito.any(EmployeeDto.class)))
                .thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.post("/app/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value("employee first name"));
    }

    @Test
    void updateEmployee() throws Exception {
        String json = objectMapper.writeValueAsString(dto);
        Mockito.when(employeeService.updateEmployee(Mockito.anyLong(), Mockito.any(EmployeeDto.class)))
                .thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.put("/app/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value("employee first name"));
    }

    @Test
    void deleteEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/app/employee/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void getEmployeeProjection() throws Exception {
        projection = new EmployeeProjection() {
            @Override
            public String getFullName() {
                return "projection name";
            }

            @Override
            public String getPosition() {
                return "projection position";
            }

            @Override
            public String getDepartmentName() {
                return "projection dept";
            }
        };
        Mockito.when(employeeService.getEmployeeProjection(Mockito.anyLong())).thenReturn(projection);
        mockMvc.perform(MockMvcRequestBuilders.get("/app/employee/projection/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName")
                        .value("projection name"));
    }
}