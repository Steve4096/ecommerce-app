package com.example.demo.DTOs;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    @Pattern(regexp = "^[A-Za-z ]+$",message = "Product name can only contain letters and spaces")
    private String productName;
    private String productLine;
    private String productScale;
    private String productDescription;
    private String productVendor;

    @Min(value = 0,message = "Quantity should be 0 or more")
    private int quantityInStock;
    private BigDecimal buyingPrice;
    private BigDecimal MSRP;
}
