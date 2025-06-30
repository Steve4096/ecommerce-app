package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeUpdateResponseDTO {
    private String employeeNumber;
    private Map<String,Map<String,Object>> updatedFields;

}
