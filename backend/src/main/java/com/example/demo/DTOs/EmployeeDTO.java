package com.example.demo.DTOs;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

        @Size(min = 4,max = 10,message = "Extension should be between 4 and 10 characters")
        private String extension;
        private String officeCode;
        private String reportsTo;

        @Pattern(regexp = "^[A-Za-z ]+$",message = "Job title can only contain letters and spaces")
        private String jobTitle;
}
