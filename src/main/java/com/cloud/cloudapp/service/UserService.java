package com.cloud.cloudapp.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloud.cloudapp.entity.Role;
import com.cloud.cloudapp.entity.Users;
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

	public Users findUserByEmail(String email) 
	{
		return userRepository.findByEmail(email);
	}
	public Users findUserByEmailToCheckMsg(String emailFromQuery,String messageFromQuery){
		return userRepository.findUserByEmailToCheckMsg(emailFromQuery,messageFromQuery);
	}
	
	public List<Users> findAll()
	{
		return userRepository.findAll();
	}
	
	public Users getOne(Integer id) 
	{
		return userRepository.getOne(id);
	}
	
	public void saveUser(Users users) 
	{
		users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
		users.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		users.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(users);
	}
	
	public void saveOnlyUser(Users users) 
	{
		userRepository.save(users);
	}
	
	public List<Users> findByNameAndLastName(String name, String lastName)
	{
		return userRepository.findByNameAndLastName(name, lastName);
	}
	
	public List<Users> findByName(String name)
	{
		return userRepository.findByName(name);
	}

	public List<Users> findByProvince(String province)
	{
		return userRepository.findByProvince(province);
	}
}