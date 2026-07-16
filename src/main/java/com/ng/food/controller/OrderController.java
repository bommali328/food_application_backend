package com.ng.food.controller;

import com.ng.food.model.Order;
import com.ng.food.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	// 1. కస్టమర్ కొత్త ఆర్డర్ పెట్టినప్పుడు డేటాబేస్ లో సేవ్ చేసే POST API
	// Endpoint: POST /api/orders
	@PostMapping
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		Order savedOrder = orderRepository.save(order);
		return ResponseEntity.ok(savedOrder);
	}

	// 2. ఓనర్ డ్యాష్‌బోర్డ్ లో అన్ని ఆర్డర్లను చూపించే GET API
	// Endpoint: GET /api/orders/all
	@GetMapping("/all")
	public ResponseEntity<List<Order>> getAllOrdersForOwner() {
		List<Order> orders = orderRepository.findAll();
		return ResponseEntity.ok(orders);
	}
}