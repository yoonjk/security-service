package com.demo.microservices.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.microservices.model.Role;
import com.demo.microservices.model.User;

public interface UserService extends UserDetailsService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User> getUsers();
}
