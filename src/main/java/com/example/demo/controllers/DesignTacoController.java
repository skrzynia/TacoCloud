package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.classes.Ingredient;
import com.example.demo.classes.Ingredient.Type;
import com.example.demo.classes.Order;
import com.example.demo.classes.Taco;
import com.example.demo.repos.JDBCIngredientRepository;
import com.example.demo.repos.JDBCTacoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	JDBCIngredientRepository ingredientRepo;
	JDBCTacoRepository tacoRepo;
	
	@Autowired
	public DesignTacoController(JDBCIngredientRepository ingredientRepo, JDBCTacoRepository tacoRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> list = new ArrayList<Ingredient>();
		ingredientRepo.findAll().forEach(i -> list.add(i));
			
		Type[] types = Ingredient.Type.values();
		
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType(list,type));
		}
		model.addAttribute("taco", new Taco());
		
		return "design";
	}
	
	@PostMapping
	public String processDesign(@Valid Taco design , Errors errors, @ModelAttribute Order order) {
		
		if(errors.hasErrors())
			return "design";
		
		Taco saved = tacoRepo.save(design);
		order.addDesign(saved);
		
		log.info("Przetwarzanie projektu taco " + design);
		return "redirect:/orders/current";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> list, Type type){
		
		return list.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
	
}
