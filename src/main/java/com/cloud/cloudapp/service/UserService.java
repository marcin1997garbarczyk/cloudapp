package com.cloud.cloudapp.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloud.cloudapp.entity.Role;
import com.cloud.cloudapp.entity.User;
import com.cloud.cloudapp.repository.RoleRepository;
import com.cloud.cloudapp.repository.UserRepository;

@Service("userService")
public class UserService 
{
	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	
	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) 
	{
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public User findUserByEmail(String email) 
	{
		return userRepository.findByEmail(email);
	}
	
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
	public User getOne(Integer id) 
	{
		return userRepository.getOne(id);
	}
	
	public void saveUser(User user) 
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	public void saveOnlyUser(User user) 
	{
		userRepository.save(user);
	}
	
	public List<User> findByNameAndLastName(String name, String lastName)
	{
		return userRepository.findByNameAndLastName(name, lastName);
	}
	
	public List<User> findByName(String name)
	{
		return userRepository.findByName(name);
	}
}