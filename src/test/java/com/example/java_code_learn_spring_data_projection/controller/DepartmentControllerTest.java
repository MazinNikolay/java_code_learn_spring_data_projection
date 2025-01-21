package com.example.java_code_learn_spring_data_projection.controller;

import com.example.java_code_learn_spring_data_projection.dto.DepartmentDto;
import com.example.java_code_learn_spring_data_projection.model.Department;
import com.example.java_code_learn_spring_data_projection.service.impl.DepartmentServiceImpl;
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

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DepartmentServiceImpl service;

    private Department department;
    private DepartmentDto dto;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .id(1L)
                .name("dept name")
                .build();
        dto = DepartmentDto.builder()
                .name("dto name")
                .build();
    }

    @Test
    void getDepartment() throws Exception{
        Mockito.when(service.getDepartment(Mockito.anyLong())).thenReturn(department);
        mockMvc.perform(MockMvcRequestBuilders.get("/app/department/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value("dept name"));
    }

    @Test
    void createDepartment() throws Exception{
        String json = objectMapper.writeValueAsString(dto);
        Mockito.when(service.createDepartment(Mockito.any(DepartmentDto.class)))
                .thenReturn(department);
        mockMvc.perform(MockMvcRequestBuilders.post("/app/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value("dept name"));
    }

    @Test
    void updateDepartment() throws Exception{
        String json = objectMapper.writeValueAsString(dto);
        Mockito.when(service.updateDepartment(Mockito.anyLong(), Mockito.any(DepartmentDto.class)))
                .thenReturn(department);
        mockMvc.perform(MockMvcRequestBuilders.put("/app/department/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value("dept name"));
    }

    @Test
    void deleteDepartment() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/app/department/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}