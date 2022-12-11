package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import com.example.demo.repository.ProductRepository;

@RestController
public class ProductController2 {
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/products2")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		productRepository.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	@GetMapping("/products2")
	public List<Product> searchAllProduct(){
		return productRepository.findAll();
	}
	
	@DeleteMapping("/products2/{id}")
	public void deleteProduct(@PathVariable("id") int id) {
		productRepository.deleteById(id);
	}
	
	@GetMapping("/products2/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
		Product product = productRepository.findById(id).get();
		return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/products2/type/{type}")
	public List<Product> getProductByType(@PathVariable("type") String type) {
		return productRepository.findByType(type);
	}
	
	
	
	
	
	
	
	
}
