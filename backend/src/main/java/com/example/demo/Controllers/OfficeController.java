package com.example.demo.Controllers;

import com.example.demo.Models.Office;
import com.example.demo.Services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @PostMapping("/register")
    public ResponseEntity<Office> registerOffice(@RequestBody Office office){
        Office registeredOffice=officeService.registerOffice(office);

        //Saves and shows you the path/Id to which the office has been saved to/under
        URI location= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/offices/{officeCode}").buildAndExpand(registeredOffice.getOfficeCode()).toUri();

        //Returns a 201 http response(the data has been successfully saved)
        return ResponseEntity.created(location).body(registeredOffice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Office> fetchOfficeById(@PathVariable String officeCode){
        Optional<Office> office=officeService.getOfficeById(officeCode);

        if(office.isPresent()){
            return ResponseEntity.ok(office.get());
        }
        return ResponseEntity.noContent().build();
    }

   @GetMapping("/fetchAll")
    public ResponseEntity<List<Office>> fetchAllOffices(){
        List<Office> offices=officeService.fetchAllOffices();
        if(offices.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(officeService.fetchAllOffices());
   }

   @DeleteMapping("/delete/{officeCode}")
    public ResponseEntity<?> deleteOffice(@PathVariable String officeCode){
        Optional<Office> deletedOffice=officeService.deleteOffice(officeCode);
        if(deletedOffice.isPresent()){
            Office office= deletedOffice.get();
            Map<String,Object> response=new HashMap<>();
            response.put("Message:","Office deleted successfully");
            response.put("ID:",office.getOfficeCode());
            response.put("City",office.getCity());
            response.put("Address Line 1:",office.getAddressLine1());
            response.put("Address Line 2",office.getAddressLine2());
            return ResponseEntity.ok(response);
        }
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("Error","Office not found");
        errorResponse.put("Office code",deletedOffice.get().getOfficeCode());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
   }
}
