package com.example.demo.repository;

import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmRepository extends JpaRepository<Farm, Integer> {
  List<Farm> findAllByUser(User user);
}