package com.example.demo;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.user.User;
import com.example.demo.repository.user.UserRepository;

@Service
public class AppUserDetailService implements UserDetailsService {
// checking login information
    @Autowired
    private UserRepository er;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      User user = er.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password("{noop}"+user.getPassword())
                .authorities(Collections.emptyList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }}
