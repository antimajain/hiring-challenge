package com.example.demo.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "userPetInfo")
public class UserPetInfo {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id")
	private String id;
	@Column(name = "pet_id")
	private String petId;
	@Column(name = "petcount")
	private int count = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public UserPetInfo() {
		super();
	}
	
	public UserPetInfo(String id,String petId, User user) {
		super();
		this.id=id;
		this.petId = petId;
		this.user = user;
	}

	public UserPetInfo(String petId, User user) {
		super();
		this.petId = petId;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public String getPetId() {
		return petId;
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}

	public int getCount() {
		return count;
	}

	public void addCount(int c) {
		this.count = c;
	}

	@Override
	public String toString() {
		return "UserPetInfo [id=" + id + ", petId=" + petId + "]";
	}

}
