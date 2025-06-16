package com.example.demo.Controllers;

import com.example.demo.Models.Customer;
import com.example.demo.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

@PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer){
    Customer registeredCustomer=customerService.registerCustomer(customer);
    return ResponseEntity.ok(registeredCustomer);
}

@GetMapping("/findCustomer/{customerNumber}")
    public ResponseEntity<Customer> findCustomer (@PathVariable Long customerNumber){
    return ResponseEntity.ok(customerService.getCustomer(customerNumber));
}

//    @GetMapping("/findCustomerByName/{firstName}")
//    public ResponseEntity<Customer> findCustomerByName (@PathVariable String firstName){
//    return ResponseEntity.ok(customerService.getCustomerByName(firstName));
//    }

    @GetMapping("/findCustomerByName")
    public ResponseEntity<List<Customer>> findCustomerByName (@RequestParam String firstName){
        return ResponseEntity.ok(customerService.getCustomerByName(firstName));
    }

    @GetMapping("/fetchAllCustomers")
    public ResponseEntity<List<Customer>> fetchExistingCustomers(){
    return ResponseEntity.ok(customerService.fetchExistingCustomers());
    }

}
