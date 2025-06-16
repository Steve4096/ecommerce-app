package com.example.demo.Repositories;

import com.example.demo.Models.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {
    Optional<ProductLine> findByTextDescription (String textDescription);
}
