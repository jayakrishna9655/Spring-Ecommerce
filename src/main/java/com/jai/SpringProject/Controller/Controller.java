package com.jai.SpringProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jai.SpringProject.Entite.Product;
import com.jai.SpringProject.Service.ProductService;

@RestController
@RequestMapping("/api")
public class Controller {
	
	@Autowired
	private ProductService service;
	
	@RequestMapping("/")
	public String greate() {
		return "hi im working jai";
	}
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProduct(){
		return new ResponseEntity<>(service.getAllProduct(),HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		
		Product product =service.getProductById(id);
		if(product !=null) {
			return new ResponseEntity<>(product,HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		
	}

}
