package com.example.demo.Controllers;

import com.example.demo.DTOs.ProductDTO;
import com.example.demo.Models.Product;
import com.example.demo.Services.ProductsService;
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
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/register")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductDTO productDTO){
        Product registeredProduct= productsService.saveProduct(productDTO);

        //Saves and shows you the path/Id to which the product line has been saved to/under
        URI location= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/product/{productCode}").buildAndExpand(registeredProduct.getProductCode()).toUri();

        //Saves and returns a http status of 201(Created)
        return ResponseEntity.created(location).body(registeredProduct);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Product>> fetchExistingProducts(){
        return ResponseEntity.ok(productsService.fetchExistingProducts());
    }

    @DeleteMapping("/delete/{productCode}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productCode){
       Optional<Product> exists=productsService.checkExistence(productCode);
        Map<String,Object> response=new HashMap<>();
       if(exists.isPresent()){
           Product productObtained=exists.get();
           productsService.deleteProduct(productCode);
           response.put("message","product deleted successfully");
           response.put("productCode",productObtained.getProductCode());
           response.put("productName",productObtained.getProductName());
           return ResponseEntity.ok(response);
       }else {
           response.put("error","product not found");
           response.put("productCode",productCode);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
       }
    }
}
