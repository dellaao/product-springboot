package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductControllerCRUD {
	@Autowired
	ProductService productService;
	
	@PostMapping("/my-products")
	public Product saveProduct(@RequestBody Product product) {
		return productService.save(product);
	}
	
	@GetMapping("/my-products")
	public List<Product> findAllProduct(){
		return productService.findAll();
	}
	
	@PutMapping("/my-products/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable("id") int id) {
		return productService.updateProduct(product, id);
	}
	
	@DeleteMapping("/my-products/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		productService.deleteProductById(id);
		return "Success";
	}
	
	@GetMapping("/my-products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
		Product product = productService.findById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
}
