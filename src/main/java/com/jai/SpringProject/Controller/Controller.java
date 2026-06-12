package com.jai.SpringProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tools.jackson.databind.ObjectMapper;

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
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart("product") String productString, @RequestPart("imageFile") MultipartFile imageFile){
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			Product product = mapper.readValue(productString, Product.class);
			
			Product product1=service.addproduct(product,imageFile);
			return new ResponseEntity<>(product1,HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("product/{id}/image")
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id){
		
		Product product = service.getProductById(id);
		
		byte[] imagefile = product.getImageValue();
		
		return ResponseEntity.ok().body(imagefile);
		
	}
	
	@PostMapping(value = "product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> updateProduct(@PathVariable int id , @RequestPart Product product ,@RequestPart("imageFile") MultipartFile imageFile){
		
		Product product1;
		try {
			product1 = service.getUpdatedProduct(id,product,imageFile);
		} catch (Exception e) {
			return new ResponseEntity<>("not updated",HttpStatus.BAD_REQUEST);
		}
		if(product1 !=null) {
			return new ResponseEntity<>("updated",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("not updated",HttpStatus.FAILED_DEPENDENCY);
		}
	}

	@PostMapping(value = "product/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProduct(@PathVariable int id , @RequestBody Product product){
		
		Product product1;
		try {
			product1 = service.getUpdatedProduct(id, product, null);
		} catch (Exception e) {
			return new ResponseEntity<>("not updated",HttpStatus.BAD_REQUEST);
		}
		if(product1 !=null) {
			return new ResponseEntity<>("updated",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("not updated",HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@DeleteMapping("product/delete/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable int id){
		Product product = service.getProductById(id);
		if(product !=null) {
			
			service.deleteProductById(id);
			
			return new ResponseEntity<String>("Deleted",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Not Deleted",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("product/search/{keyword}")
	public ResponseEntity<List<Product>> searchProduct(@PathVariable String keyword){
		List<Product> product=service.searchProduct(keyword);
		return new ResponseEntity<List<Product>>(product,HttpStatus.OK );
	}

}
