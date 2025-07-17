package com.example.demo.DTOs;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductLineDTO {

   // @Pattern(regexp = "^[A-Za-z ]+$",message = "ProductLine text description can only contain letters and spaces")
    private String textDescription;
    private String htmlDescription;
    private String imagePath;
}
