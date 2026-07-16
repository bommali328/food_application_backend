package com.ng.food.controller;

import com.ng.food.model.FoodItem;
import com.ng.food.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class FoodItemController {

	@Autowired
	private FoodItemRepository foodItemRepository;

	@GetMapping
	public ResponseEntity<List<FoodItem>> getMenu() {
		return ResponseEntity.ok(foodItemRepository.findAll());
	}
}