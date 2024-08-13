package com.example.demo.services;

import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class FarmService {

  private FarmRepository farmRepository;
  private UserRepository UserRepository;

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  public Farm gatFarmById(int id) {
    return farmRepository.findById(id).orElse(null);
  }

  public List<Farm> allFarms() {
    return farmRepository.findAllByUser(authenticatedUser());
  }

  private User authenticatedUser() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return UserRepository.findByUsername(username);
  }

  public void deleteFarmById(int id) {
    farmRepository.deleteById(id);
  }
}
