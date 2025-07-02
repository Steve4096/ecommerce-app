package com.example.demo.Controllers;


import com.example.demo.DTOs.ProductLineDTO;
import com.example.demo.DTOs.ProductLineUpdateResponseDTO;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Models.ProductLine;
import com.example.demo.Services.ProductLineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/productLine")
public class ProductLineController {

    @Autowired
    private ProductLineService productLineService;
    ProductLine productLine;

//    @PostMapping("/registerProductLine")
//    public ResponseEntity<ProductLine> registerProductLine(@RequestBody ProductLine productLine){
//        ProductLine registeredProductLine=productLineService.saveProductLine(productLine);
//        return ResponseEntity.ok(productLine);
//    }
    @PostMapping("/register")
    public ResponseEntity<ProductLine> registerProductLine(@Valid @RequestBody ProductLineDTO productLineDTO){
        ProductLine registeredProductLine=productLineService.saveProductLine(productLineDTO);

        //Saves and shows you the path/Id to which the product line has been saved to/under
        URI location= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/productLine/{productLine}").buildAndExpand(registeredProductLine.getProductLine()).toUri();

        //Saves and returns a http status of 201(Created)
        return ResponseEntity.created(location).body(registeredProductLine);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<ProductLine>> fetchExistingProductLines(){
        return ResponseEntity.ok(productLineService.fetchExistingProductLines());
    }

    @PatchMapping("/edit/{productLine}")
    public ResponseEntity<ProductLineUpdateResponseDTO> updateExistingProductLine(@PathVariable String productLine, @Valid @RequestBody ProductLineDTO productLineDTO){
        return productLineService.updateExistingProductLine(productLine,productLineDTO).map(ResponseEntity::ok)
                .orElseThrow(()->new NotFoundException("Product Line with ID "+productLine+" not found."));
    }


    @DeleteMapping("/delete/{productLine}")
    public ResponseEntity<?> deleteProductLine(@PathVariable String productLine){
        Optional<ProductLine> deletedProductLine=productLineService.checkExistence(productLine);
        Map<String,Object> response=new HashMap<>();
        if(deletedProductLine.isPresent()){
            ProductLine productLineObtained = deletedProductLine.get();
            productLineService.deleteProductLine(productLine);
            response.put("message","ProductLine deleted successfully");
            response.put("productLineID", productLineObtained.getProductLine());
            response.put("textDescription", productLineObtained.getTextDescription());
            return ResponseEntity.ok(response);
        }else {
            response.put("error","Product Line not found");
            response.put("productLineID",productLine);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


}


