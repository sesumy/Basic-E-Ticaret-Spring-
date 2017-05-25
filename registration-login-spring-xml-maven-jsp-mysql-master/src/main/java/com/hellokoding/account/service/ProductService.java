package com.hellokoding.account.service;
import java.util.List;

import org.hibernate.Query;

import com.hellokoding.account.model.Product;

public interface ProductService {
	
		public List<Product> getAllProducts();
		public Product getProductById(Long id);
		public void addProduct(Product product);
		public int findProductMaxId();
	    
}
