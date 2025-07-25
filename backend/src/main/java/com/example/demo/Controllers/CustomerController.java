package com.example.demo.Controllers;

import com.example.demo.Models.Customer;
import com.example.demo.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

@PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer){
    Customer registeredCustomer=customerService.registerCustomer(customer);

    //Saves and shows you the path/Id to which the customer has been saved to/under
    URI location= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/customers/{customerNumber}").buildAndExpand(registeredCustomer.getCustomerNumber()).toUri();

    //Returns a 201 http response(the data has been successfully saved)
    return ResponseEntity.created(location).body(registeredCustomer);
}

@GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomer (@PathVariable Long customerNumber){
    Customer customer=customerService.getCustomerById(customerNumber);
    return ResponseEntity.ok(customer);
}

    @GetMapping("/findByName")
    public ResponseEntity<List<Customer>> findCustomerByName (@RequestParam String firstName){
        return ResponseEntity.ok(customerService.getCustomerByName(firstName));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String,Long>> countExistingCustomers(){
    Long count= customerService.countExistingCustomers();
    Map<String,Long> response= Collections.singletonMap("count",count);
    return ResponseEntity.ok(response);
    }

//    @GetMapping("/count")
//    public ResponseEntity<Map<String,Long>> countExistingCustomers(){
//        Long count= customerService.countExistingCustomers();
//        Map<String,Long> response=new HashMap<>();
//        response.put("count",count);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Customer>> fetchExistingCustomers(){
    return ResponseEntity.ok(customerService.fetchExistingCustomers());
    }

    @DeleteMapping("/delete/{customerNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerNumber){
        Optional<Customer> deletedCustomer=customerService.deleteCustomerById(customerNumber);
        if(deletedCustomer.isPresent()){
            Customer customer= deletedCustomer.get();
            Map<String,Object> response=new HashMap<>();
            response.put("Message","Customer deleted successfully");
            response.put("Id:",customer.getCustomerNumber());
            response.put("Name:",customer.getFirstName()+" "+customer.getLastName());
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }
}
