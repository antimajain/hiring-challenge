package com.example.demo.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserPetInfo;

public interface UserPetInfoRepository extends JpaRepository<UserPetInfo, String> {

	List<UserPetInfo> findByUser(User user);

	UserPetInfo findByPetIdAndUser(String petId, User user);

}
