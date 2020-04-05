package com.cloud.cloudapp.entity;


public class Message {

		private int id;
		
		private String province;
		
		private String notification;
		
		public Message() 
		{
			super();
		}

		public Message(int id, String province, String notification) 
		{
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

		public String getProvince() 
		{
			return province;
		}

		public void setProvince(String province) 
		{
			this.province = province;
		}

		public String getNotification() 
		{
			return notification;
		}

		public void setNotification(String notification) 
		{
			this.notification = notification;
		}
	}