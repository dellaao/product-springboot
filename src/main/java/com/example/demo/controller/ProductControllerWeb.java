package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.impl.ProductServiceImpl;

@Controller
public class ProductControllerWeb {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/")
    public String getListProduct(Map<String, Object> map) {
        String uri = "http://localhost:8989/my-products";
        RestTemplate restTemplate = new RestTemplate();
        
        List<?> products = restTemplate.getForObject(uri, List.class);
        map.put("productLists", products);

       return "index";
    }
	
	@GetMapping("/update-product-form/{id}")
    public String getUpdateProductForm(Map<String, Object> map, @PathVariable(value = "id") int id) {
		String uri = "http://localhost:8989/my-products/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Product> productResponse = restTemplate.getForEntity(uri, Product.class, Map.of("id",id));
	    
	    map.put("product", productResponse.getBody());
	    return "update-product-form";
    }
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(value = "id") int id) {
		String uri = "http://localhost:8989/my-products/" + id;
        RestTemplate restTemplate = new RestTemplate();
        
        restTemplate.delete(uri);
		return "redirect:/";
        
	}
	
	@PostMapping("/update-product/{id}")
	public String updateProduct(@ModelAttribute("product") Product product, @PathVariable(value = "id") int id) {
		String uri = "http://localhost:8989/my-products/" + id;
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.put(uri, product, Map.of("id",id));
		return "redirect:/";
	}
	
	@GetMapping("/add-product-form")
	public String addProductForm() {
		return "add-item-form";
	}
	
	@PostMapping("/add-product")
	public String addProduct(@ModelAttribute("product") Product product, Map<String, Object> map) {
		String uri = "http://localhost:8989/my-products/";
		RestTemplate restTemplate = new RestTemplate();
		Product newProduct = restTemplate.postForObject(uri, product, Product.class);
		
		return "redirect:/";
	}
	
	@GetMapping("/search")
    public String findProductById(Map<String, Object> map, @ModelAttribute("id") int id) {
		String uri = "http://localhost:8989/my-products/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Product> productResponse = restTemplate.getForEntity(uri, Product.class, Map.of("id",id));
	    
	    map.put("product", productResponse.getBody());
	    return "find-by-id";
    }
	
}
