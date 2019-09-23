package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.contoller.PetController;
import com.example.demo.exception.UserExistsException;
import com.example.demo.model.pet.Pet;
import com.example.demo.model.user.Cart;
import com.example.demo.model.user.User;
import com.example.demo.service.PetService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PetControllerTest {

	@InjectMocks
	PetController pc;
	@Mock
	PetService ps;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void registerTest() throws UserExistsException {

		User u = new User("1", "user", "user");
		when(ps.register(u)).thenReturn(u);
		assertEquals(u, pc.registerUser(u));
		// Verify that the query method was called on the es mock
		verify(ps).register(u);
	}

	@Test
	public void homePageTest() {
		List<Pet> pets = new ArrayList<>();
		Pet p1 = new Pet("1", "Dog", 30);
		pets.add(p1);
		when(ps.getPets()).thenReturn(pets);
		assertEquals(pets,pc.homePage());
	}
	
	@Test
	public void addPetTest() {
		when(ps.addPet("1",1)).thenReturn("Added");
		assertEquals("Added",pc.addPet("1",1));
	}
	
	
	@Test
	public void removePetTest() {
		when(ps.removePet("1")).thenReturn("Successfully deleted");
		assertEquals("Successfully deleted",pc.removePet("1"));
	}
	
	@Test
	public void displayCartTest() {
		List<Cart> c=new ArrayList<Cart>();
		Cart cart1 = new Cart("1","bird",30,0);
		c.add(cart1);
		when(ps.displayCart()).thenReturn(c);
		assertEquals(c,pc.displayCart());
		
	}
	
	
	
}
