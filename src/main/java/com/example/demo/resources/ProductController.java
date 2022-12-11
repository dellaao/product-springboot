package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;

@RestController
public class ProductController {
	
	private static List<Product> products = new ArrayList();
	
	static {
		Product p1 = new Product(1, "Aqua", "Minuman", 5000.00);
		Product p2 = new Product(2, "Sate", "Makanan", 10000.00);
		Product p3 = new Product(3, "Pensil", "ATK", 2500.00);
		products.add(p1);
		products.add(p2);
		products.add(p3);
	}
	
	@GetMapping("/products")
	public List<Product> searchAllProduct(){
		return products;
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
		for (Product p : products) {
			if (p.getId() == id) {
				return new ResponseEntity<Product>(p, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		product.setId(new Random().nextInt());
		products.add(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	
	
	
}
