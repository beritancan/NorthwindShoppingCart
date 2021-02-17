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

import com.example.northwind.business.abctracts.ICartService;
import com.example.northwind.entities.concretes.Cart;

@RestController
@RequestMapping("/api/v1")
public class CartsController {
	
	@Autowired
	ICartService cartService;
	
	
	@PostMapping("/carts")
	public Cart addToCart (@RequestBody Cart cart) {
		System.out.println(cart);
		return cartService.addToCart(cart);
		
}	
	@PutMapping("/carts/{id}")
	public ResponseEntity<Cart> update(@PathVariable(value="id") int cartId, @RequestBody Cart cart) throws Exception {
	
		Cart cartToUpdate = cartService.getById(cartId);
		
		cartToUpdate.setProductId(cart.getProductId());
		cartToUpdate.setProductName(cart.getProductName());
		cartToUpdate.setCustomerId(cart.getCustomerId());
		cartToUpdate.setCustomerName(cart.getCustomerName());
		cartToUpdate.setNumberOfProduct(cart.getNumberOfProduct());
	    
		ResponseEntity<Cart> updatedCart = cartService.update(cartToUpdate);
	
		return updatedCart;
	}
	
	@PutMapping("/carts/{id}/{customerId}/{countDecrease}")
	public String delete(@PathVariable(value="id") int productId, @PathVariable(value="customerId") String customerId, @PathVariable(value="countDecrease") int countDecrease) throws Exception {
	    return cartService.removeProductsFromCart(productId, customerId, countDecrease);
	}
	
	@GetMapping("/carts/{customerId}")
	public List<Cart> listCartOfCustomer(@PathVariable(value="customerId") String customerId) {
		return cartService.listCartOfCustomer(customerId);
	}
}
