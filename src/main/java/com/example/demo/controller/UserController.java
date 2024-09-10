package com.example.demo.controller;

import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.repository.FarmRepository;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

  @Autowired private UserService userService;
  @Autowired private FarmRepository farmRepository;

  @PostMapping("/inscription")
  public String registerUser(@RequestBody User user) {
    userService.register(user);

    return null;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User userRequest) {
    User user = userService.authenticate(userRequest.getUsername(), userRequest.getPassword());
    if (user != null) {
      String credentials = user.getUsername() + ':' + userRequest.getPassword();
      String s = Base64.getEncoder().encodeToString(credentials.getBytes());
      HashMap<String, String> response = new HashMap<>();
      response.put("token", s);
      response.put("username", user.getUsername());

      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
  }

  @GetMapping("/user")
  public ResponseEntity<User> getUserByEmail(@RequestParam String username) {
    User user = userService.findByUsername(username);
    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/user/farms")
  public List<Farm> userFarms() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<Farm> allByUser = farmRepository.findAllByUser(user);
    return allByUser;
  }
}
