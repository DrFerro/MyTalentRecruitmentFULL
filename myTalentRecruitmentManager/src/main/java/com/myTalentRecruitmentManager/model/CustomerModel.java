package com.myTalentRecruitmentManager.model;

public class CustomerModel {

	private long id;
	private String name;
	private String lastName1;
	private String lastName2;
	private String email;
	private String phone;
	private boolean anonymous;
	private boolean worker;
	
	public CustomerModel() {}
	
	public CustomerModel(String name, String lastName1, String lastName2, String email, String phone, boolean anonymous, boolean worker) {
		this.name = name;
		this.lastName1 = lastName1;
		this.lastName2 = lastName2;
		this.email = email;
		this.phone = phone;
		this.anonymous = anonymous;
		this.worker = worker;
	}
	
	public CustomerModel(long id, String name, String lastName1, String lastName2, String email, String phone, boolean anonymous, boolean worker) {
		this.id = id;
		this.name = name;
		this.lastName1 = lastName1;
		this.lastName2 = lastName2;
		this.email = email;
		this.phone = phone;
		this.anonymous = anonymous;
		this.worker = worker;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName1() {
		return lastName1;
	}
	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}
	public String getLastName2() {
		return lastName2;
	}
	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isAnonymous() {
		return anonymous;
	}
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	public boolean isWorker() {
		return worker;
	}
	public void setWorker(boolean worker) {
		this.worker = worker;
	}
}
