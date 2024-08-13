package com.example.demo.repository;

import com.example.demo.entity.Culture;
import com.example.demo.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CultureRepository extends JpaRepository<Culture, Integer> {}
