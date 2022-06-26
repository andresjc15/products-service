package com.ajcp.product.model.service;

import com.ajcp.product.model.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();
    public Product findById(Long id);

    public Product save(Product product);

}
