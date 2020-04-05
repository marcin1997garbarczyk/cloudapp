package com.cloud.cloudapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cloud.cloudapp.entity.Role;

@Entity
public class Users {
	
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

	@Column(name="province")
	private String province;
	
	@Column(name="active")
	private int active;
	
	@Column(name="lastNotificationForProvince")
	private String lastNotificationForProvince;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	
	
	public Users() {
		super();
		this.type="USER";
		this.lastNotificationForProvince = "";
	}


	


	public Users(int id,
			@Email(message = "Please provide a valid Email") @NotEmpty(message = "Please provide an email") String email,
			@NotEmpty(message = "Please provide your password") @Length(min = 5, message = "*Your password must have at least 5 characters") String password,
			@NotEmpty(message = "*Please provide your name") String name,
			@NotEmpty(message = "*Please provide your last name") String lastName,
			@NotEmpty(message = "*Please provide your province") String province,String type, int active,String lastNotificationForProvince,
			Set<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.type = "USER";
		this.active = active;
		this.roles = roles;
		this.province = province;
		this.lastNotificationForProvince = "";
	}



	public String getLastNotificationForProvince() {
		return lastNotificationForProvince;
	}


	public void setLastNotificationForProvince(String lastNotificationForProvince) {
		this.lastNotificationForProvince = lastNotificationForProvince;
	}


	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}


	public int getActive() {
		return active;
	}





	public void setActive(int active) {
		this.active = active;
	}





	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
