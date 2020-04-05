package com.cloud.cloudapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="role_id")
		private int id;
		
		@Column(name="role")
		private String province;
		
		@Column(name="notification")
		private String notification;
		
		public Message() {
			super();
		}

		public Message(int id, String province, String notification) {
			super();
			this.id = id;
			this.province = province;
			this.notification = notification;
		}

		public int getId() 
		{
			return id;
		}

		public void setId(int id) 
		{
			this.id = id;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getNotification() {
			return notification;
		}

		public void setNotification(String notification) {
			this.notification = notification;
		}


	}