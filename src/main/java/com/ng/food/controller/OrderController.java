package com.ng.food.controller;

import com.ng.food.model.Order;
import com.ng.food.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // 🔄 ఏ యుఆర్ఎల్ కొట్టినా డైరెక్ట్‌గా మన కొత్త index.html కి వెళ్తుంది
    @GetMapping("/")
    public String homePage() {
        return "forward:/index.html";
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
    @ResponseBody
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    // 2. ఓనర్ డ్యాష్‌బోర్డ్ లో అన్ని ఆర్డర్లను చూపించే GET API
    @GetMapping("/api/orders/all")
    @ResponseBody
    public ResponseEntity<List<Order>> getAllOrdersForOwner() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }
}