package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.exception.UserExistsException;
import com.example.demo.model.pet.Pet;
import com.example.demo.model.user.Cart;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserPetInfo;
import com.example.demo.repository.pet.PetRepository;
import com.example.demo.repository.user.UserPetInfoRepository;
import com.example.demo.repository.user.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PetServiceImplTest {

	@InjectMocks
	PetServiceImpl petService;

	@Mock
	PetRepository petRepo;

	@Mock
	UserRepository userRepo;

	@Mock
	UserPetInfoRepository infoRepo;

	@Before // Run before and after every test method in the class
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void registerTest() throws UserExistsException {
		User u = new User("1", "test", "test");
		assertEquals(u, petService.register(u));

	}

	@Test(expected = UserExistsException.class)
	public void registerTestWhenUserExists() throws UserExistsException {
		User u = new User("1", "test", "test");
		when(userRepo.findByUserName("test")).thenReturn(u);
		assertEquals(u, petService.register(u));

	}

	@Test
	@WithMockUser(username = "user")
	public void getPetstest() {
		List<Pet> pets = new ArrayList<>();
		Pet p1 = new Pet("1", "Dog", 30);
		pets.add(p1);
		when(petRepo.findAll()).thenReturn(pets);
		assertEquals(pets, petService.getPets());
	}

	@Test
	@WithMockUser(username = "user")
	public void addPet() {
		User u = new User("1", "user", "user");
		UserPetInfo info = new UserPetInfo("1", "1", u);
		when(userRepo.findByUserName("user")).thenReturn(u);
		when(infoRepo.findByPetIdAndUser("1", u)).thenReturn(info);
		assertEquals("Added", petService.addPet("1", 2));

	}

	@Test
	@WithMockUser(username = "user")
	public void addPetWhenAlreadyPetDoseNotExists() {
		User u = new User("1", "user", "user");
		when(userRepo.findByUserName("user")).thenReturn(u);
		when(infoRepo.findByPetIdAndUser("1", u)).thenReturn(null);
		assertEquals("Added", petService.addPet("1", 2));

	}

	@Test
	@WithMockUser(username = "user")
	public void removePetTest() {
		User user = new User("1", "user", "user");
		UserPetInfo info = new UserPetInfo("1", "1", user);
		when(infoRepo.findByPetIdAndUser("1", user)).thenReturn(info);
		assertEquals("Successfully deleted", petService.removePet("1"));

	}

	@Test
	@WithMockUser(username = "user")
	public void displayCartTest() {
		User user = new User("1", "user", "user");
		when(userRepo.findByUserName("user")).thenReturn(user);
		UserPetInfo info = new UserPetInfo("1", "1", user);
		List<UserPetInfo> list = new ArrayList<UserPetInfo>();
		list.add(info);
		when(infoRepo.findByUser(user)).thenReturn(list);
		Pet p = new Pet("1", "bird", 30);
		when(petRepo.findById("1")).thenReturn(Optional.of(p));

		List<Cart> c = new ArrayList<Cart>();
		Cart cart1 = new Cart("1", "bird", 30, 0);
		c.add(cart1);

		assertTrue(c.equals(petService.displayCart()));
	}
}
