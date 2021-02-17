package com.example.northwind.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.northwind.business.abctracts.IOrderService;
import com.example.northwind.dataaccess.concretes.CartRepository;
import com.example.northwind.dataaccess.concretes.OrderDetailsRepository;
import com.example.northwind.dataaccess.concretes.OrderRepository;
import com.example.northwind.dataaccess.concretes.ProductRepository;
import com.example.northwind.entities.concretes.Cart;
import com.example.northwind.entities.concretes.Order;
import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.entities.concretes.Product;



@Service
public class OrderManager implements IOrderService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order add(Order order) {
		
		Order savedOrder= orderRepository.save(order);
		List<Cart> carts = cartRepository.listOfCartsByCustomer(order.getCustomerId());
		
		for (Cart cart: carts) {
			Optional<Product> productDetail = productRepository.findById(cart.getProductId());
			
			OrderDetail savedOrderDetail = new OrderDetail();
			savedOrderDetail.setOrderId(savedOrder.getOrderId());
			savedOrderDetail.setProductId(cart.getProductId());
			savedOrderDetail.setUnitPrice(productDetail.get().getUnitPrice());
			savedOrderDetail.setQuantity(cart.getNumberOfProduct());
			savedOrderDetail.setDiscount(0);
			
			orderDetailsRepository.save(savedOrderDetail);
			cartRepository.delete(cart); 
			
		} 
		
		return savedOrder;
	}
	
	
	

}
