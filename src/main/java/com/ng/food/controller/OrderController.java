package com.ng.food.controller;

import com.ng.food.model.Order;
import com.ng.food.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; // 👈 @Controller కి మార్చాం
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // 👈 ఇక్కడ @RestController బదులు @Controller అని పెట్టాలి నవీన్
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	// 🔄 కస్టమర్ యాప్ ఓపెన్ చేయగానే డైరెక్ట్‌గా లాగిన్ పేజీకి వెళ్తుంది
	@GetMapping("/")
	public String homePage() {
		return "forward:/login.html";
	}

	@GetMapping("/login.html")
	public String loginPage() {
		return "forward:/login.html";
	}

	@GetMapping("/index.html")
	public String indexPage() {
		return "forward:/index.html";
	}

	@GetMapping("/owner.html")
	public String ownerPage() {
		return "forward:/owner.html";
	}

	// 1. కస్టమర్ కొత్త ఆర్డర్ పెట్టినప్పుడు సేవ్ చేసే POST API
	@PostMapping("/api/orders")
	@ResponseBody // 👈 ఇది పెట్టడం వల్ల ఈ API డేటాను JSON లాగా రిటర్న్ చేస్తుంది
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		Order savedOrder = orderRepository.save(order);
		return ResponseEntity.ok(savedOrder);
	}

	// 2. ఓనర్ డ్యాష్‌బోర్డ్ లో అన్ని ఆర్డర్లను చూపించే GET API
	@GetMapping("/api/orders/all")
	@ResponseBody // 👈 దీనికి కూడా @ResponseBody యాడ్ చేశాం
	public ResponseEntity<List<Order>> getAllOrdersForOwner() {
		List<Order> orders = orderRepository.findAll();
		return ResponseEntity.ok(orders);
	}
}