package com.cloud.cloudapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cloud.cloudapp.entity.Users;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Users, Integer> 
{
	Users findByEmail(String email);
	List<Users> findAll();
	List<Users> findByNameAndLastName(String name, String lastName);
	List<Users> findByName(String name);
}