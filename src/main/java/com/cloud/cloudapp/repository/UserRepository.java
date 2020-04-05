package com.cloud.cloudapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloud.cloudapp.entity.Users;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Users, Integer> 
{
	Users findByEmail(String email);
	List<Users> findByProvince(String province);
	List<Users> findAll();
	List<Users> findByNameAndLastName(String name, String lastName);
	List<Users> findByName(String name);
	
	@Query("SELECT u.id, u.lastNotificationForProvince, u.email FROM Users u WHERE u.email =: emailFromQuery and u.lastNotificationForProvince !=: messageFromQuery")
	Users findUserByEmailToCheckMsg(@Param("emailFromQuery")String emailFromQuery,@Param("messageFromQuery")String messageFromQuery);

}