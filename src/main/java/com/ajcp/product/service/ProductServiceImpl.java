package com.ajcp.product.service;

import com.ajcp.library.common.model.entity.Product;
import com.ajcp.product.model.repository.ProductRepository;
import com.ajcp.product.model.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) { return productRepository.save(product); }

    @Override
    public Product update(Long id, Product product) {
        return productRepository.findById(id).map(p -> {
            p.setName(product.getName());
            return productRepository.save(p);
        }).orElseGet(() -> {
            return null;
        });
    }

    @Override
    public void deletedById(Long id) { productRepository.deleteById(id); }

}
