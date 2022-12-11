package com.example.demo.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepo;
	
	@Override
	public Product save(Product product) {
		return productRepo.save(product) ;
	}

	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepo.findAll();
	}

	@Override
	public Product updateProduct(Product product, int id) {
		Product productDB = productRepo.findById(id).get();
		
		if (Objects.nonNull(product.getName())&& !"".equalsIgnoreCase(product.getName())) {
			productDB.setName(product.getName());
		}
		if (Objects.nonNull(product.getPrice())&& !"".equals(product.getPrice())) {
			productDB.setPrice(product.getPrice());
		}
		if (Objects.nonNull(product.getType())&& !"".equalsIgnoreCase(product.getType())) {
			productDB.setType(product.getType());
		}
		
		return productRepo.save(productDB);
	}

	@Override
	public void deleteProductById(int id) {
		productRepo.deleteById(id);
		
	}

	@Override
	public Product findById(int id) {
		return productRepo.findById(id).get();
	}

	@Override
	public List<Product> findByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}
 
//	@Override
//	public List<Product> findByType(String type) {
//		return productRepo.findByType(type);
//	}

}
