package com.example.northwind.business.concretes;

import org.springframework.stereotype.Service;

import com.example.northwind.business.abctracts.IOrderDetailService;
import com.example.northwind.entities.concretes.OrderDetail;

@Service
public class OrderDetailsManager implements IOrderDetailService {

	@Override
	public OrderDetail add(OrderDetail orderDetail) {
		
		return null;
	}

}
