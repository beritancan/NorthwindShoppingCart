package com.example.northwind.business.abctracts;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.northwind.entities.concretes.Cart;

public interface ICartService {
	
	Cart getById(int CartId) throws Exception;
	Cart addToCart(Cart cart);
	ResponseEntity <Cart> update(Cart cart) throws Exception;
	String removeProductsFromCart(int productId, String customerId, int countDecrease );
	Map<String, Boolean> delete(Cart cart) throws Exception;
	List<Cart> listCartOfCustomer(String CustomerId);


}

 