package com.example.java_code_learn_spring_data_projection.controller;

import com.example.java_code_learn_spring_data_projection.dto.DepartmentDto;
import com.example.java_code_learn_spring_data_projection.dto.EmployeeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private EmployeeDto employeeDto;
    private DepartmentDto departmentDto;

    @BeforeEach
    void setUp() {
        employeeDto = EmployeeDto.builder()
                .firstName("dto firstname")
                .lastName("dto lastname")
                .salary(45.0)
                .position("dto position")
                .departmentId(1l)
                .build();
        departmentDto = DepartmentDto.builder()
                .name("dto name")
                .build();
    }

    @Test
    @Order(2)
    void getEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/app/employee/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value("dto firstname"));
    }

    @Test
    @Order(1)
    void createEmployee() throws Exception {
        String jsonEmployee = objectMapper.writeValueAsString(employeeDto);
        String jsonDepartment = objectMapper.writeValueAsString(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/app/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDepartment))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value("dto name"));
        mockMvc.perform(MockMvcRequestBuilders.post("/app/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEmployee))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value("dto firstname"));
    }

    @Test
    @Order(4)
    void updateEmployee() throws Exception {
        employeeDto.setFirstName("updated firstname");
        String json = objectMapper.writeValueAsString(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/app/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value("updated firstname"));
    }

    @Test
    @Order(5)
    void deleteEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/app/employee/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @Order(3)
    void getEmployeeProjection() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/app/employee/projection/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName")
                        .value("dto firstname dto lastname"));
    }
}