package com.example.demo.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.classes.Taco;
import com.example.demo.controllers.Ingredient.Type;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> list = Arrays.asList(new Ingredient("FLTO", "pszenna", Type.WRAP), new Ingredient("COTO", "kukurydziana", Type.WRAP),
			new Ingredient("GRBF", "mielona wolowina", Type.PROTEIN), new Ingredient("CARN", "kawalki miesa", Type.PROTEIN), 
			new Ingredient("TMTO", "pomidory koktajlowe", Type.VEGGIES), new Ingredient("LETC", "salata", Type.VEGGIES),
			new Ingredient("CHED", "cheddar", Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Type.CHEESE), 
			new Ingredient("SLSA", "pikantny sos pomidorowy", Type.SAUCE), new Ingredient("SRCR", "smietana", Type.SAUCE));
		
		Type[] types = Ingredient.Type.values();
		
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType(list,type));
		}
		model.addAttribute("design", new Taco());
		
		return "design";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> list, Type type){
		
		return list.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
	
}
