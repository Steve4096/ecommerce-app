package com.example.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Getter
@Setter
@Entity
public class ProductLine {

    @Id
    @Column(nullable = false,unique = true)
    private String productLine;

    @Column(nullable = false,unique = true)
    @Pattern(regexp = "^[A-Za-z ]+$",message = "ProductLine text description can only contain letters and spaces")
    private String textDescription;

    @Column
    private String htmlDescription;

    @Column
    private String imagePath;
}
