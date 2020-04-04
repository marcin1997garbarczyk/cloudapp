package com.cloud.cloudapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Email(message="Please provide a valid Email")
	@NotEmpty(message="Please provide an email")
	@Column(name="email")
	private String email;
	
	@NotEmpty(message="Please provide your password")
	@Length(min=5,message="*Your password must have at least 5 characters")
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name="last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	
	@Column(name="type")
	private String type;

	
	
	public User() {
		super();
	}

	public User(int id,
			@Email(message = "Please provide a valid Email") @NotEmpty(message = "Please provide an email") String email,
			@NotEmpty(message = "Please provide your password") @Length(min = 5, message = "*Your password must have at least 5 characters") String password,
			@NotEmpty(message = "*Please provide your name") String name,
			@NotEmpty(message = "*Please provide your last name") String lastName, String type) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}