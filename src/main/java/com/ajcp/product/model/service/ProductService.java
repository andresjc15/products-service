package com.ajcp.product.model.service;

import com.ajcp.library.common.model.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();
    public Product findById(Long id);
    public Product save(Product product);

    public Product update(Long id, Product product);
    public void deletedById(Long id);

}
