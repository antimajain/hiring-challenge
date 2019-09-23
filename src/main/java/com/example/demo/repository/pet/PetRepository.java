package com.example.demo.repository.pet;

import org.springframework.stereotype.Repository;

import com.example.demo.model.pet.Pet;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {
	

}
