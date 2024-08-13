package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  public User register(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User findByUsername(String email) {
    return userRepository.findByUsername(email);
  }

  public User authenticate(String username, String password) {
    User user = findByUsername(username);
    if (user != null && passwordEncoder.matches(password, user.getPassword())) {
      return user;
    }
    return null;
  }
}
