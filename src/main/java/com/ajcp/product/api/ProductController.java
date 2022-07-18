package com.ajcp.product.api;

import com.ajcp.library.common.model.entity.Product;
import com.ajcp.product.model.service.ProductService;
import com.ajcp.product.util.PropertyValue;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${path.products}")
@AllArgsConstructor
public class ProductController {

    //private PropertyValue value;

    private final Environment environment;

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<Product> products = productService.findAll()
                .stream()
                .peek(product -> {
                    product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                    //product.setPort(Integer.parseInt(value.port));
                })
                .collect(Collectors.toList());
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) throws InterruptedException {
        Product product = productService.findById(id);
        product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        if (id.equals(10L)) {
            throw new IllegalStateException("Producto no encontrado!");
        }
        if (id.equals(7L)) {
            TimeUnit.SECONDS.sleep(5L);
        }
        //product.setPort(Integer.parseInt(value.port));

        //boolean ok = false;
        //if (!ok) { throw new RuntimeException("Nose pudo cargar el producto"); }
        /*
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        */
        return new ResponseEntity<                                Product>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody Product product) {
        return new ResponseEntity<Product>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        return new ResponseEntity<Product>(productService.update(id, product), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.deletedById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
