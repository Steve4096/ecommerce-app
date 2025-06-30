package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {

    @Id
    @Column(unique = true)
    private String employeeNumber;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    private String extension;

    @ManyToOne
    @JoinColumn(name="office_code",nullable = false)
    private Office office;

    @ManyToOne
    @JoinColumn(name="reports_to")
    private Employee reportsTo;

    @Column(nullable = false)
    private String jobTitle;
}
