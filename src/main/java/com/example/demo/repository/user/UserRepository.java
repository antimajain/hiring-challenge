package com.example.demo.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByUserName(String username);

}
