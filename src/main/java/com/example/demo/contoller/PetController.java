package com.example.demo.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.exception.UserExistsException;
import com.example.demo.model.pet.Pet;
import com.example.demo.model.user.Cart;
import com.example.demo.model.user.User;
import com.example.demo.service.PetService;

@Controller
@RequestMapping("/petstore")
public class PetController {

	@Autowired
	PetService petService;

	@RequestMapping(value = "/register")
	public String registerPage() {
		return "register";
	}

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public @ResponseBody User registerUser(@RequestBody User user) throws UserExistsException {
		return petService.register(user);

	}

	// login
	@RequestMapping(value = "/loginForm")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/home")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public @ResponseBody List<Pet> homePage() {
		return petService.getPets();
	}

	@RequestMapping(value = "/addPet/{id}", method = RequestMethod.POST)
	public @ResponseBody String addPet(@PathVariable("id") String petId,@RequestParam("count") int c){
		return petService.addPet(petId,c);

	}
	
	@RequestMapping(value = "/removePet/{id}", method = RequestMethod.POST)
	public @ResponseBody String removePet(@PathVariable("id") String petId) {
		return petService.removePet(petId);

	}
	
	@RequestMapping(value = "/cart")
	public String cart() {
		return "cart";
	}
	
	@RequestMapping(value = "/cartDisplay", method = RequestMethod.GET)
	public @ResponseBody List<Cart> displayCart() {
		return petService.displayCart();
	}

}
