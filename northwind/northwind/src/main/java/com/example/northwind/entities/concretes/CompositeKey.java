package com.example.northwind.entities.concretes;

import java.io.Serializable;

import javax.persistence.Column;

public class CompositeKey implements Serializable {
	
	@Column(name="order_id")
	private int orderId;
	@Column(name="product_id")
	private int productId;

	}
	
