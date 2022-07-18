package com.ajcp.product;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
/*@OpenAPIDefinition(
		info = @Info(title = "${swagger.title}",
				version = "${swagger.version}",
				description = "${swagger.description}"))*/
@OpenAPIDefinition(
		info = @Info(title = "Product service",
				version = "1.0.0",
				description = "Product Microservice"))
@EntityScan({"com.ajcp.library.common"})
public class ProductsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsServiceApplication.class, args);
	}

}
