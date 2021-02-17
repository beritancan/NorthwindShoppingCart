package com.example.northwind.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.northwind.entities.concretes.Product;

public interface ProductRepository extends JpaRepository< Product, Integer> {
	
	 @Query( value = "select count(*)from products where category_id = :categoryId ", nativeQuery = true)       
	  public  int productCountByCategoryId(@Param("categoryId") int categoryId);
	 
	 
	 
}