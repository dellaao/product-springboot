package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	List<Product> findByType(String type);
//	Product findById(int id);
	//dibawah ini untuk menjalankan ProductController2
	List<Product> findAll();
//	Product updateProduct(Product product, int id);
}
