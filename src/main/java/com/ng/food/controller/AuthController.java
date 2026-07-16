package com.ng.food.controller;

import com.ng.food.model.User;
import com.ng.food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // 1. సైన్అప్ API - డేటాబేస్ లో యూజర్ ని సేవ్ చేస్తుంది
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // మొబైల్ నంబర్ ఆల్రెడీ ఉందో లేదో చెక్ చేస్తున్నాం
        if (userRepository.findByMobile(user.getMobile()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ఈ మొబైల్ నంబర్ ఆల్రెడీ రిజిస్టర్ అయి ఉంది!");
        }
        
        // డేటాబేస్ లో సేవ్ చేస్తున్నాం
        userRepository.save(user);
        return ResponseEntity.ok("రిజిస్ట్రేషన్ విజయవంతమైంది!");
    }

    // 2. లాగిన్ API - డేటాబేస్ లో యూజర్ పాస్‌వర్డ్ ని వెరిఫై చేస్తుంది
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        // ఇక్కడ మనం ఫ్రంట్-ఎండ్ లో పంపే username ని mobile కింద మ్యాప్ చేసుకుంటాం
        Optional<User> userOpt = userRepository.findByMobile(loginRequest.getMobile());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // పాస్‌వర్డ్ మ్యాచ్ అయిందో లేదో చెక్ చేస్తున్నాం
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok("లాగిన్ విజయవంతమైంది!");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("తప్పుడు మొబైల్ నంబర్ లేదా పాస్‌వర్డ్!");
    }
}