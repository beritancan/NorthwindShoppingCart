package com.example.northwind.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.northwind.business.abctracts.IOrderService;
import com.example.northwind.entities.concretes.Cart;
import com.example.northwind.entities.concretes.Order;


@RestController
@RequestMapping("/api/v1")
public class OrdersController {
	
	@Autowired
	IOrderService orderService;
	
	@PostMapping("/orders")
	public Order add (@RequestBody Order order ) {
		return orderService.add(order);
		
	} 
	
	
	

}
