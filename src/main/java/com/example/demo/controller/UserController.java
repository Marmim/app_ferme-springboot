package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping("/inscription")
  public String registerUser(@RequestBody User user) {
    String credentials = user.getUsername() + ':' + user.getPassword();
    String s = Base64.getEncoder().encodeToString(credentials.getBytes());
    userService.register(user);
    return s;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User userRequest) {
    User user = userService.authenticate(userRequest.getUsername(), userRequest.getPassword());
    if (user != null) {
      return ResponseEntity.ok(user);
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
}
