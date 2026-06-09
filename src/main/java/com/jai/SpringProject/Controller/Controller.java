package com.jai.SpringProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Product> getAllProduct(){
		return service.getAllProduct();
		
	}

}
