package com.ajcp.product.api;

import com.ajcp.product.model.entity.Product;
import com.ajcp.product.model.service.ProductService;
import com.ajcp.product.util.PropertyValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${path.products}")
@AllArgsConstructor
public class ProductController {

    private PropertyValue value;

    private final Environment environment;

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<Product> products = productService.findAll()
                .stream()
                .map(product -> {
                    //product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                    product.setPort(Integer.parseInt(value.port));
                    return product;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        //product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        product.setPort(Integer.parseInt(value.port));
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody Product product) {
        return new ResponseEntity<Product>(productService.save(product), HttpStatus.OK);
    }

}
