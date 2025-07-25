package com.example.demo.Services;

import com.example.demo.DTOs.ProductDTO;
import com.example.demo.Exceptions.DuplicateException;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Models.Product;
import com.example.demo.Models.ProductLine;
import com.example.demo.Repositories.ProductLineRepository;
import com.example.demo.Repositories.ProductRepository;
import com.example.demo.UtilityClasses.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productsRepository;

    @Autowired
    private ProductLineRepository productLineRepository;

    public Product saveProduct(ProductDTO productDTO){
        String generatedID;
        do {
            generatedID= IdGenerator.generateProductID();
        }while (productsRepository.existsById(generatedID));
        Product product=new Product();
        product.setProductCode(generatedID);
        if(productsRepository.findByProductName(productDTO.getProductName()).isPresent()){
            throw new DuplicateException("A product of the same name already exists.Please enter another name");
        }
        product.setProductName(productDTO.getProductName());

        ProductLine productLine=productLineRepository.findById(productDTO.getProductLine())
                        .orElseThrow(()->new NotFoundException("ProductLine with ID "+productDTO.getProductLine()+" not found"));
        product.setProductLine(productLine);
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductVendor(productDTO.getProductVendor());
        product.setProductScale(productDTO.getProductScale());
        product.setQuantityInStock(productDTO.getQuantityInStock());
        product.setBuyingPrice(productDTO.getBuyingPrice());
        return productsRepository.save(product);
    }

    public Long countExistingProducts(){
        return productsRepository.count();
    }

    public List<Product> fetchExistingProducts(){
        return productsRepository.findAll();
    }

//    public boolean checkExistence(String productCode){
//        if(productsRepository.existsById(productCode)){
//            return true;
//        }
//        return false;
//    }

    public Optional<Product> checkExistence(String productCode){
        Optional<Product> exists=productsRepository.findById(productCode);
        if(exists.isPresent()){
            return exists;
        }
        return Optional.empty();
    }

    public void deleteProduct(String productCode){
        productsRepository.deleteById(productCode);
    }
}
