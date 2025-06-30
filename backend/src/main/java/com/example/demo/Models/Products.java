package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productCode;

    @Column(nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name="product_line",nullable = false)
    private ProductLine productLine;

    private String productScale;

    @Column(nullable = false)
    private String productDescription;

    @Column(nullable = false)
    private String productVendor;

    @Column(nullable = false)
    private int quantityInStock;

    @Column(nullable = false)
    private BigDecimal buyingPrice;
    private BigDecimal MSRP;
}
