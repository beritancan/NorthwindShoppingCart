package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.northwind.business.abctracts.ICartService;

import com.example.northwind.dataaccess.concretes.CartRepository;
import com.example.northwind.entities.concretes.Cart;

@Service
public class CartManager implements ICartService{

	 
	@Autowired
	private CartRepository cartRepository;
	
	public Cart getById(int cartId) throws Exception {
		Cart cartToFind = cartRepository.findById(cartId)
				.orElseThrow(()-> new Exception("No cart with id: "+ cartId));
		if (cartToFind == null) {
			return null;
		}
		return cartToFind;
	}
		
	@Override
	public Cart addToCart (Cart cart)  {
			System.out.println(cart.getCartId());
			
			List<Cart> updateCart = cartRepository.isThereAnyProductInCart(cart.getCustomerId(), cart.getProductId());
			System.out.println(updateCart);
			
			if (updateCart.size()>0) {
			    Cart updateSavedCart = updateCart.get(0);
				int count = updateSavedCart.getNumberOfProduct() + cart.getNumberOfProduct();

				updateSavedCart.setNumberOfProduct(count);
				return cartRepository.save(updateSavedCart);
			} else {
				return cartRepository.save(cart);
			}
	}	
			
			@Override
			public ResponseEntity<Cart> update(Cart cart) throws Exception {
				Cart cartToUpdate = cartRepository.findById(cart.getCartId())
						.orElseThrow(()-> new Exception("No cart with id: "+ cart.getCartId()));
			    
				Cart updatedCart = cartRepository.save(cartToUpdate);
				
				return ResponseEntity.ok(updatedCart); 
			}


			@Override
			public String removeProductsFromCart(int productId, String customerId, int countDecrease) {
				List<Cart> cart = cartRepository.isThereAnyProductInCart(customerId, productId);
				int numberOfProduct = cart.get(0).getNumberOfProduct()-countDecrease;
				Cart cartUpdate= cart.get(0);
				if( numberOfProduct>0 ) {
					
					cartUpdate.setNumberOfProduct(numberOfProduct);
					cartRepository.save(cartUpdate);
				} else {
					cartRepository.delete(cartUpdate);		
			   }
				return "İşlem başarılı bir şekilde tamamlandı";
			}
			
			@Override
			public Map<String, Boolean> delete( Cart cart) throws Exception {
				Cart cartDelete = cartRepository.findById(cart.getCartId())
					    .orElseThrow(()-> new Exception("No cart with id: " + cart.getCartId()));
					    
				        cartRepository.delete(cartDelete);
					    Map<String, Boolean> response = new HashMap <>();
					    response.put("Bu ürün sepetinizden çıkarıldı", Boolean.TRUE);
				        return response;	

			} 


			@Override
			public List<Cart> listCartOfCustomer(String CustomerId) {
				return cartRepository.listOfCartsByCustomer(CustomerId);
			}


			 

		
	}

	
		


