package com.example.demo.services;

import com.example.demo.entity.Culture;
import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.repository.CultureRepository;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FarmService {

  @Autowired private FarmRepository farmRepository;
  @Autowired private CultureRepository cultureRepository;
  private UserRepository UserRepository;

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  public Farm gatFarmById(int id) {
    return farmRepository.findById(id);
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

  public Farm updateFarm(int id, Farm farmDetails) {
    // Retrieve the existing farm or throw an exception if not found
    Farm farm = farmRepository.findById(id);
    // Update the farm's properties
    farm.setNom(farmDetails.getNom());
    farm.setDouar(farmDetails.getDouar());
    farm.setRegion(farmDetails.getRegion());
    farm.setLongitude(farmDetails.getLongitude());
    farm.setLatitude(farmDetails.getLatitude());
    farm.setCommune(farmDetails.getCommune());

    // Update the farm's cultures
    Set<Culture> updatedCultures = new HashSet<>();
    for (Culture culture : farmDetails.getCultures()) {
      if (culture.getId() != 0) { // Check if ID is non-zero
        // Check if the culture exists
        Optional<Culture> optionalExistingCulture = cultureRepository.findById(culture.getId());
        if (optionalExistingCulture.isPresent()) {
          Culture existingCulture = optionalExistingCulture.get();
          existingCulture.setNom(culture.getNom()); // Assuming 'nom' is a property to update
          // Update other properties of the culture if needed
          updatedCultures.add(cultureRepository.save(existingCulture));
        } else {
          // Handle the case where the culture does not exist
          // For example, you might want to log this or throw an exception
          System.out.println("Culture not found with id " + culture.getId());
          // Optionally, you could add some logic to handle this case
        }
      } else {
        // Add new culture
        updatedCultures.add(cultureRepository.save(culture));
      }
    }

    // Set the updated cultures
    farm.setCultures(updatedCultures);

    // Save and return the updated farm
    return farmRepository.save(farm);
  }
}
