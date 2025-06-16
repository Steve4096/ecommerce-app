package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Getter
@Setter
@Entity
public class ProductLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,unique = true)
    private Long productLine;

    @Column(nullable = false,unique = true)
    private String textDescription;

    @Column
    private String htmlDescription;

    @Column
    private String imagePath;
}
