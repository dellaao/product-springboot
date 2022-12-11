package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Product;

public interface ProductService {
	Product save(Product product);
	List<Product> findAll();
	Product updateProduct(Product product, int id);
	void deleteProductById(int id);
	Product findById(int id);
	List<Product> findByType(String type);
}
