package com.ng.food.model;

import jakarta.persistence.*;

@Entity
@Table(name = "food_items")
public class FoodItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private double price;
	private String calories;
	private String description;
	private String img;

	// Constructors
	public FoodItem() {
	}

	public FoodItem(String name, double price, String calories, String description, String img) {
		this.name = name;
		this.price = price;
		this.calories = calories;
		this.description = description;
		this.img = img;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}