package com.example.demo.repos;

import com.example.demo.classes.Ingredient;

public interface JDBCIngredientRepository {

	Iterable<Ingredient> findAll();
	Ingredient findById(String id);
	Ingredient save(Ingredient ingredient);
}
