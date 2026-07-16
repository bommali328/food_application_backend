package com.ng.food.controller;

import com.ng.food.model.Order;
import com.ng.food.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // 1. కొత్త ఆర్డర్ ప్లేస్ చేసే API (పేమెంట్ వివరాల మెసేజ్ తో)
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        // డేటాబేస్ లో ఆర్డర్ సేవ్ చేస్తున్నాం (కొత్త పేమెంట్ ఫీల్డ్స్ తో సహా)
        Order savedOrder = orderRepository.save(order);

        // --- ప్రొఫెషనల్ లైవ్ ట్రాకింగ్ URL ---
        String trackingUrl = "https://food-application-backend-1.onrender.com/track.html?id=" + savedOrder.getId();

        // డిఫాల్ట్ వాల్యూస్ చెక్ చేయడం (నల్ పాయింటర్ రాకుండా)
        String payMode = savedOrder.getPaymentMode() != null ? savedOrder.getPaymentMode() : "COD";
        String payStatus = savedOrder.getPaymentStatus() != null ? savedOrder.getPaymentStatus() : "PENDING";

        // --- పేమెంట్ వివరాలతో కూడిన సరికొత్త మెッセージ ఫార్మాట్ ---
        String notificationMessage = "New Order Placed - NG Foods\n" +
                "🆔 Order ID: " + savedOrder.getId() + "\n" +
                "📦 Order Details: " + savedOrder.toString() + "\n" + 
                "💳 Payment Mode: " + payMode + "\n" +       // పేమెంట్ మోడ్ (COD/ONLINE)
                "📊 Payment Status: " + payStatus + "\n" +   // పేమెంట్ స్టేటస్ (PENDING/SUCCESS)
                "💰 Total: ₹" + savedOrder.getTotalAmount() + "/-\n" +
                "🌐 Track: " + trackingUrl;

        // మెసేజ్ పంపే మెథడ్ రన్ చేస్తున్నాం
        sendNotification(notificationMessage);

        return ResponseEntity.ok(savedOrder);
    }

    // 2. ఓనర్ డ్యాష్‌బోర్డ్ కోసం అన్ని ఆర్డర్ల API
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    // 3. లైవ్ ట్రాకింగ్ కోసం సింగిల్ ఆర్డర్ API
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4. నోటిఫിക്കేషన్ పంపే మెథడ్
    private void sendNotification(String message) {
        System.out.println("========== SENDING NOTIFICATION ==========");
        System.out.println(message);
        System.out.println("==========================================");
    }
}