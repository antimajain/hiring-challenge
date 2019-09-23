package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserExistsException;
import com.example.demo.model.pet.Pet;
import com.example.demo.model.user.Cart;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserPetInfo;
import com.example.demo.repository.pet.PetRepository;
import com.example.demo.repository.user.UserPetInfoRepository;
import com.example.demo.repository.user.UserRepository;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	PetRepository petRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserPetInfoRepository userPetInfoRepo;

	private String getLoggedInUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@Override
	public User register(User user) throws UserExistsException {
		if (userRepo.findByUserName(user.getUserName()) != null)
			throw new UserExistsException("User Exists");
		userRepo.save(user);
		return user;

	}

	@Override
	public List<Pet> getPets() {
		return petRepo.findAll();
	}

	@Override
	public String addPet(String petId, int c) {
		String username = getLoggedInUserName();
		User user = userRepo.findByUserName(username);
		UserPetInfo info = userPetInfoRepo.findByPetIdAndUser(petId, user);
		if (info == null)
			info = new UserPetInfo(petId, user);
		info.addCount(c);
		userPetInfoRepo.save(info);

		return "Added";
	}

	@Override
	public String removePet(String petId) {
		String username = getLoggedInUserName();
		User user = userRepo.findByUserName(username);
		UserPetInfo info = userPetInfoRepo.findByPetIdAndUser(petId, user);
		userPetInfoRepo.delete(info);
		return "Successfully deleted";
	}

	@Override
	public List<Cart> displayCart() {
		String username = getLoggedInUserName();
		User user = userRepo.findByUserName(username);
		List<UserPetInfo> info = userPetInfoRepo.findByUser(user);
		List<Cart> c = new ArrayList<>();
		for (UserPetInfo f : info) {
			Cart cart = new Cart();
			String id = f.getPetId();

			Pet p = petRepo.findById(id).orElse(null);
			cart.setId(p.getId());
			cart.setProduct(p.getPetName());
			cart.setCost(p.getCost());
			cart.setCount(f.getCount());
			c.add(cart);
		}

		return c;
	}

}
