package com.example.northwind.entities.concretes;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.northwind.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity	
@Table(name="carts")
public class Cart implements IEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO) 
	@Column(name="cart_id")
	private int cartId;
	@Column(name="product_id")
	private int productId;
	@Column(name="product_name")
	private String productName;
	@Column(name = "customer_id")
	private String customerId;
	@Column(name = "customer_name")
	private String customerName;
	@Column(name = "number_of_product")
	private int numberOfProduct;
}