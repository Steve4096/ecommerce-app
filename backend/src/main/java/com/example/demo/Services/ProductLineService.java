package com.example.demo.Services;

import com.example.demo.DTOs.ProductLineDTO;
import com.example.demo.DTOs.ProductLineUpdateResponseDTO;
import com.example.demo.Exceptions.ProductLineExistsException;
import com.example.demo.Models.Employee;
import com.example.demo.Models.ProductLine;
import com.example.demo.Repositories.ProductLineRepository;
import com.example.demo.UtilityClasses.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

@Service
public class ProductLineService {

//    @Autowired
//    private ProductLine productLine;

    @Autowired
    private ProductLineRepository productLineRepository;


    public ProductLine saveProductLine(ProductLineDTO productLineDTO){
        String generatedID;
        do {
            generatedID= IdGenerator.generateProductLineID();
        }while (productLineRepository.existsById(generatedID));
        ProductLine productLine=new ProductLine();
        productLine.setProductLine(generatedID);
        if(productLineRepository.findByTextDescription(productLineDTO.getTextDescription()).isPresent()){
            throw new ProductLineExistsException("A product of the same description already exists");
        }
            productLine.setTextDescription(productLineDTO.getTextDescription());
            productLine.setHtmlDescription(productLineDTO.getHtmlDescription());
            productLine.setImagePath(productLineDTO.getImagePath());
            return productLineRepository.save(productLine);
    }

    public List<ProductLine> fetchExistingProductLines(){
        return productLineRepository.findAll();
    }

    public Optional<ProductLineUpdateResponseDTO> updateExistingProductLine(String productLineID,ProductLineDTO productLineDTO){
        Optional<ProductLine> optionalProductLine=productLineRepository.findById(productLineID);
        if (optionalProductLine.isEmpty()){
            return Optional.empty();
        }
        ProductLine productLine=optionalProductLine.get();
        Map<String,Map<String, Object>> updatedFields=new HashMap<>();

        if(productLineDTO.getTextDescription() !=null && !productLineDTO.getTextDescription().equals(productLine.getTextDescription())){
            updatedFields.put("Text description",Map.of(
                    "from",productLine.getTextDescription(),
                    "to",productLineDTO.getTextDescription()
            ));
            productLine.setTextDescription(productLineDTO.getTextDescription());
            System.out.println("Updated text description from "+productLine.getTextDescription()+" to "+productLineDTO.getTextDescription());
        }

        if(productLineDTO.getHtmlDescription() !=null && !productLineDTO.getHtmlDescription().equals(productLine.getHtmlDescription())){
            updatedFields.put("Html description",Map.of(
                    "from",productLine.getHtmlDescription(),
                    "to",productLineDTO.getHtmlDescription()
            ));
            productLine.setHtmlDescription(productLineDTO.getHtmlDescription());
            System.out.println("Updated html description from "+productLine.getHtmlDescription()+" to "+productLineDTO.getHtmlDescription());
        }

        if(productLineDTO.getImagePath() !=null && !productLineDTO.getImagePath().equals(productLine.getImagePath())){
            updatedFields.put("Image path",Map.of(
                    "from",productLine.getImagePath(),
                    "to",productLineDTO.getImagePath()
            ));
            productLine.setImagePath(productLineDTO.getImagePath());
            System.out.println("Updated image path from "+productLine.getImagePath()+" to "+productLineDTO.getImagePath());
        }
        return Optional.of(new ProductLineUpdateResponseDTO(productLine.getProductLine(),updatedFields));
    }

    public Optional<ProductLine> checkExistence(String productLine){
        Optional<ProductLine> exists=productLineRepository.findById(productLine);
        if(exists.isPresent()){
            return exists;
        }
        return Optional.empty();
    }

    public void deleteProductLine(String productLine){
         productLineRepository.deleteById(productLine);
    }
}
