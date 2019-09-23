package com.example.demo.service;

import java.util.List;
import com.example.demo.exception.UserExistsException;
import com.example.demo.model.pet.Pet;
import com.example.demo.model.user.Cart;
import com.example.demo.model.user.User;

public interface PetService {

	User register(User user) throws UserExistsException;

	List<Pet> getPets();

	String addPet(String petId, int c);

	String removePet(String petId);

	List<Cart> displayCart();

}
