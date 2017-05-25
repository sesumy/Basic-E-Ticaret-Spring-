package com.hellokoding.account.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellokoding.account.model.Product;
import com.hellokoding.account.model.User;

import java.lang.String;
import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long>  {

    public List<Product> findAll();
    public Product getProductById(Long id);

    
}
