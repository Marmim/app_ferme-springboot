package com.example.demo.controller;

import com.example.demo.entity.Culture;
import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.repository.CultureRepository;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.FarmService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FarmController {

  private FarmService farmService;
  private final FarmRepository farmRepository;
  private final CultureRepository cultureRepository;
  private final UserRepository userRepository;

  public FarmController(
      FarmRepository farmRepository,
      CultureRepository cultureRepository,
      UserRepository userRepository) {
    this.farmRepository = farmRepository;
    this.cultureRepository = cultureRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/{id}")
  public Farm gatFarmById(@PathVariable int id) {
    return farmService.gatFarmById(id);
  }

  @PostMapping("/create-farm")
  public String addFarm(@RequestBody CreateFarmDTO dto) {
   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    User user = userRepository.findByUsername(username);
    Farm farm = new Farm();
    farm.setNom(dto.nom);
    farm.setDouar(dto.douar);
    farm.setRegion(dto.region);
    farm.setLongitude(dto.longitude);
    farm.setLatitude(dto.latitude);
    farm.setCommune(dto.commune);
    farm.setUser(user);
      List<Culture> cultures = new ArrayList<>();
    dto.cultures.forEach(
        cultureDTO -> {
          Culture culture = new Culture();
          culture.setNom(cultureDTO.nom);
          cultureRepository.save(culture);
          cultures.add(culture);
        });
    farm.setCultures(new HashSet<>(cultures));
    farmRepository.save(farm);

    return "Farm added";
  }
}
