package com.example.demo.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String postalCode;

    @Column
    private String country;

    @Column
    private String salesRepEmployeeNumber;

    @Column
    private String creditLimit;

}
