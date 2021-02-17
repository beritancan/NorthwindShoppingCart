package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.northwind.business.abctracts.IProductService;
import com.example.northwind.dataaccess.concretes.ProductRepository;
import com.example.northwind.entities.concretes.Cart;
import com.example.northwind.entities.concretes.Product;

@Service // bu bir service classı çünkü
public class ProductManager implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;

	
	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}


	@Override
	public Product getById(int id) throws Exception {
		Product productToFind = productRepository.findById(id)
				.orElseThrow(()-> new Exception("No product with id: "+ id ));
		if (productToFind == null) {
			return null;
		}
		return productToFind;
	}


	@Override
	public String add(Product product) {
		System.out.println(product.getProductName());
		int count = productRepository.productCountByCategoryId(product.getCategoryId());
		if(product.getProductName().length()<2) {
			return " En az 2 karakterden oluşan bir ürün ismi girmelisiniz!";
		}else if(count > 10){
			return "Bu kategoride en fazla 10 ürün bulunabilir!";
			
		}
		else {
			productRepository.save(product) ;
			return "Ürün başarılı bir şekilde veritabanına eklenmiştir!";
		}	

	}


	@Override
	public ResponseEntity<Product> update(Product product) throws Exception {
		Product productToUpdate = productRepository.findById(product.getId())
				.orElseThrow(()-> new Exception("No product with id: "+ product.getId()));
	
		Product updatedProduct= productRepository.save(productToUpdate);
		
		return ResponseEntity.ok(updatedProduct);
		
	}


	@Override
	public Map<String, Boolean> delete(Product product) throws Exception {
		Product productDelete = productRepository.findById(product.getId())
			    .orElseThrow(()-> new Exception("No product with id: " + product.getId()));
			    
			    productRepository.delete(productDelete);
			    Map<String, Boolean> response = new HashMap <>();
			    response.put("Silindi", Boolean.TRUE);
		        return response;
	}
	

	@Override
	public int productCountByCategoryId(int categoryId) {
		return 0;
	}
	
}
