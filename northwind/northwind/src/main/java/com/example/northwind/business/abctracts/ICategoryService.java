package com.example.northwind.business.abctracts;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.northwind.entities.concretes.Category;

public interface ICategoryService {
	
	List<Category> getAll();
	Category getById(int categoryId) throws Exception;
	Category add(Category category);
	ResponseEntity<Category> update(Category category) throws Exception;
	Map<String, Boolean> delete(Category category) throws Exception;
}
