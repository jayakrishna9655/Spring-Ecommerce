package com.jai.SpringProject.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	public Product addproduct(Product product, MultipartFile imageFile) throws IOException {
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageValue(imageFile.getBytes());
		return repo.save(product);
	}

	public Product getUpdatedProduct(int id, Product product, MultipartFile imageFile) throws IOException {
		product.setId(id);
		
		if (imageFile != null && !imageFile.isEmpty()) {
			product.setImageValue(imageFile.getBytes());
			product.setImageType(imageFile.getContentType());
			product.setImageName(imageFile.getOriginalFilename());
		} else {
			Product existingProduct = repo.findById(id).orElse(null);
			if (existingProduct != null) {
				product.setImageValue(existingProduct.getImageValue());
				product.setImageType(existingProduct.getImageType());
				product.setImageName(existingProduct.getImageName());
			}
		}
		return repo.save(product);
	}

	public void deleteProductById(int id) {
		
		 repo.deleteById(id);
	}

}
