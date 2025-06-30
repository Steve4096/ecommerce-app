package com.example.demo.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Office {

    @Id
    @Column(name = "Office_code",unique = true)
    private String officeCode;

    @Column(name = "City", nullable = false)
    private String city;

    @Column(name = "Phone", unique = true,nullable = false)
    private String phone;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String state;

    @Column
    private String country;

    @Column(unique = false)
    private int postalCode;

    @Column
    private String territory;
}
