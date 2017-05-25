package com.hellokoding.account.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hellokoding.account.model.Product;
import com.hellokoding.account.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;	
	
	@Autowired
	EntityManager em;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long id) {
		return productRepository.getProductById(id);
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public int findProductMaxId() {
		 List<Product> query= em.createQuery("select max(u.id) from product u",Product.class).getResultList();
		 return 1;
	}
	
}
