package com.demo.microservices.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.microservices.common.dao.CommonDao;
import com.demo.microservices.model.Role;
import com.demo.microservices.model.User;
import com.demo.microservices.model.UserInfoRole;
import com.demo.microservices.model.UserRole;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		User u = null;
		try {
			String encodePassword = passwordEncoder.encode(user.getPassword());
			
			user.setPassword(encodePassword);
			int rc = commonDao.insert("insertUser", user);
			
			u = commonDao.selectOne("selectUser", user.getUsername());
		} catch (Exception e) {
			log.error("ERROR", e);
		}

		return u;
	}

	@Override
	public Role saveRole(Role role) {
		Role r = null;
		try {
			int rc = commonDao.insert("insertRole", role);
			
			r = commonDao.selectOne("selectRole", role.getRoleName());
		} catch (Exception e) {
			log.error("ERROR", e);
		}

		return r;
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		UserRole userRole = null;
		try {
			userRole = new UserRole(username, roleName);
			int rc = commonDao.insert("insertRoleToUser", userRole);
		} catch (Exception e) {
			log.error("ERROR", e);
		}
	}

	@Override
	public User getUser(String username) {
		User u = null;
		try {
			u = commonDao.selectOne("selectUser", username);
		} catch (Exception e) {
			log.error("ERROR", e);
		}

		return u;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = null;
		try {
			users = commonDao.selectList("selectUserAll");
		} catch (Exception e) {
			log.error("ERROR", e);
		}

		return users;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = new User(username, "");
		List<UserInfoRole> userRoles = null;
		try {
			log.info("username:{}", username);
			userRoles = commonDao.selectList("selectUserRole", username);
			
			if (userRoles == null|| userRoles.size() == 0) {
				log.error("User not found in the database");
				throw new UsernameNotFoundException("User not found in the database");
			} else {
				log.info("userRoles:{}", userRoles);
			}
		} catch (Exception e) {
			log.error("ERROR", e);
		}

		Collection<GrantedAuthority> authorities = new ArrayList();
		userRoles.forEach(role -> {
			user.setPassword(role.getPassword());
			log.info("userRole:{}", role.getRoleName());
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList());
	}
}
