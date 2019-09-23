package com.example.demo.model.pet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Pet {

	@Id
	@Column(name = "pet_id")
	private String id;
	@Column(name = "petname")
	private String petName;
	@Column(name = "cost")
	private int cost;

	public Pet() {
		super();
	}

	public Pet(String id, String petName, int cost) {
		super();
		this.id = id;
		this.petName = petName;
		this.cost = cost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + "petName=" + petName + ", cost=" + cost + "]";
	}

}
