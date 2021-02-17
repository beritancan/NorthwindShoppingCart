package com.example.northwind.api.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.northwind.business.abctracts.IProductService;
import com.example.northwind.entities.concretes.Product;

@RestController
@RequestMapping("/api/v1")
public class ProductsController {
	
	@Autowired //injection yapıyo --- ıproduct service implemente edenin serviceini tutuyo yani aslında product managerı newlemiş oluyo
	IProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAll(){
		return productService.getAll();	
	}
	

	@GetMapping("/products/{id}")
	public Product getById(@PathVariable int id) throws Exception {
		
		Product productToFind = productService.getById(id);
			
		if (productToFind == null) {
			return null;
		}
		return productToFind;
	}
	
	
	@PostMapping("/products")
		public String add (@RequestBody Product product) {
		System.out.println(product);
		return productService.add(product);
		}	
		
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> update(@PathVariable(value="id") int productId, @RequestBody Product product) throws Exception {
	
		Product productToUpdate = productService.getById(productId);
		
		productToUpdate.setCategoryId(product.getCategoryId()); 
		productToUpdate.setDiscontinued(product.getDiscontinued());
		productToUpdate.setProductName(product.getProductName());
		productToUpdate.setQuantityPerUnit(product.getQuantityPerUnit());
		productToUpdate.setReorderLevel(product.getReorderLevel());
		productToUpdate.setSupplierId(product.getSupplierId());
		productToUpdate.setUnitPrice(product.getUnitPrice());
		productToUpdate.setUnitsInStock(product.getUnitsInStock());
		productToUpdate.setUnitsOnOrder(product.getUnitsOnOrder());
	    
		ResponseEntity<Product> updatedProduct = productService.update(productToUpdate);
	
		return updatedProduct;
	}
	
	@DeleteMapping("products/{id}")
	public Map<String, Boolean> delete(@PathVariable Product product) throws Exception {
		Product productDelete = productService.getById(product.getId());
			    
			    productService.delete(productDelete);
			    Map<String, Boolean> response = new HashMap <>();
			    response.put("Silindi", Boolean.TRUE);
		        return response;	
		  }
}