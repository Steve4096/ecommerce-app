package com.example.demo.Services;

import com.example.demo.Exceptions.ProductLineExistsException;
import com.example.demo.Models.ProductLine;
import com.example.demo.Repositories.ProductLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class ProductLineService {

//    @Autowired
//    private ProductLine productLine;

    @Autowired
    private ProductLineRepository productLineRepository;

    public ProductLine saveProductLine(ProductLine productLine){
        if(productLineRepository.findByTextDescription(productLine.getTextDescription()).isPresent()){
            throw new ProductLineExistsException("A product of the same description already exists");
        }
         return productLineRepository.save(productLine);
    }

//    public ProductLine saveProductLine(ProductLine productLine){
//        Optional<ProductLine> existing=productLineRepository.findByProductLine(productLine.getProductLine());
//        if(existing.isPresent()){
//            throw new RuntimeException("Product Line already exists");
//        }
//        return productLineRepository.save(productLine);
//    }

    public List<ProductLine> fetchExistingProductLines(){
        return productLineRepository.findAll();
    }
}
