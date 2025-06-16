package com.example.demo.Controllers;


import com.example.demo.Models.ProductLine;
import com.example.demo.Services.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductLineController {

    @Autowired
    private ProductLineService productLineService;

    @PostMapping("/registerProductLine")
    public ResponseEntity<ProductLine> registerProductLine(@RequestBody ProductLine productLine){
        ProductLine registeredProductLine=productLineService.saveProductLine(productLine);
        return ResponseEntity.ok(productLine);
    }

    @GetMapping("/fetchAllProductLines")
    public ResponseEntity<List> fetchExistingProductLines(){
        return ResponseEntity.ok(productLineService.fetchExistingProductLines());
    }

    //@DeleteMapping("/deleteProductLine/{productLine}")

}


