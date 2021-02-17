package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.northwind.business.abctracts.ICategoryService;
import com.example.northwind.dataaccess.concretes.CategoryRepository;
import com.example.northwind.entities.concretes.Category;
import com.example.northwind.entities.concretes.Product;

@Service
public class CategoryManager implements ICategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getById(int categoryId) throws Exception {
		Category categoryToFind = categoryRepository.findById(categoryId)
				.orElseThrow(()-> new Exception("No category with id: "+ categoryId ));
		if (categoryToFind == null) {
			return null;
		}
		return categoryToFind;
	}

	@Override
	public Category add(Category category) {
		System.out.println(category.getCategoryId());
		return categoryRepository.save(category);

	}

	@Override
	public ResponseEntity<Category> update(Category category) throws Exception {
		Category  categoryToUpdate = categoryRepository.findById(category.getCategoryId())
				.orElseThrow(()-> new Exception("No product with id: "+ category.getCategoryId())); //// BURAYA BAKKKK
		
		categoryToUpdate.setCategoryName(category.getCategoryName());
	
		Category updatedCategory= categoryRepository.save(categoryToUpdate);
		
		return ResponseEntity.ok(updatedCategory);
	}

	@Override
	public Map<String, Boolean> delete(Category category) throws Exception {
		Category categoryDelete = categoryRepository.findById(category.getCategoryId())
			    .orElseThrow(()-> new Exception("No category with id: " + category.getCategoryId()));
			    
		categoryRepository.delete(categoryDelete);
			    Map<String, Boolean> response = new HashMap <>();
			    response.put("Silindi", Boolean.TRUE);
		        return response;
	}
	
	

}
