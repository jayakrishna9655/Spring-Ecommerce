package com.jai.SpringProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jai.SpringProject.Entite.Product;
import com.jai.SpringProject.Repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

}
