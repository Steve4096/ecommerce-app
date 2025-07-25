package com.example.demo.Services;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Models.Customer;
import com.example.demo.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
   private CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer){
        customerRepository.save(customer);
        return customer;
    }

    public Customer getCustomerById(Long customerNumber){
      return customerRepository.findById(customerNumber)
              .orElseThrow(()->new CustomerNotFoundException(customerNumber));
    }

    public List<Customer> getCustomerByName(String firstName){
        return customerRepository.findByFirstName(firstName);
//                .ifPresent(customer -> {
//                    System.out.println("Customer found "+customer.getFirstName()});
                //.orElseThrow(()->new CustomerNotFoundException(firstName));
    }

    public Long countExistingCustomers(){
        return customerRepository.count();
    }

    public List<Customer> fetchExistingCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> deleteCustomerById(Long customerNumber){
        Optional<Customer> customer=customerRepository.findById(customerNumber);
        if(customer.isPresent()){
            customerRepository.deleteById(customerNumber);
            return customer;
        }
        return Optional.empty();
    }
}
