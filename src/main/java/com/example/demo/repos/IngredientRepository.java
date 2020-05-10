package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.classes.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
