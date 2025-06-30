package com.example.demo.Controllers;

import com.example.demo.DTOs.EmployeeDTO;
import com.example.demo.DTOs.EmployeeUpdateResponseDTO;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Models.Employee;
import com.example.demo.Services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<Employee> saveNewEmployeeDetails(@RequestBody Employee employee){
        Employee registeredEmployee=employeeService.saveEmployee(employee);

        //Saves and shows you the path/Id to which the customer has been saved to/under
        URI location= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/customers/{employeeNumber}").buildAndExpand(registeredEmployee.getEmployeeNumber()).toUri();

        //Saves and returns a http status of 201(Created)
        return ResponseEntity.created(location).body(registeredEmployee);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Employee>> fetchExistingEmployees(){
       return ResponseEntity.ok(employeeService.fetchAllEmployees());
    }

    @PatchMapping("/edit/{employeeNumber}")
    public ResponseEntity<EmployeeUpdateResponseDTO> updateEmployeeDetails(@PathVariable String employeeNumber, @Valid @RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployeeDetails(employeeNumber,employeeDTO).map(ResponseEntity::ok)
                .orElseThrow(()->new NotFoundException("Employee with ID "+employeeNumber+" not found." ));
    }
}


