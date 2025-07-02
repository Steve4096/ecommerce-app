package com.example.demo.Repositories;

import com.example.demo.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products,String> {
    Optional<Products> findByProductName (String productName);
}
