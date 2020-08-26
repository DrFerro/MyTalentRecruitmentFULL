package com.myTalentRecruitment.db;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.myTalentRecruitment.db.Candidate;

@Entity
@Table (name="customer")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	
	@Column(length = 60)
	private String name;
	
	@Column(length = 60)
	private String lastName1;
	
	@Column(length = 60)
	private String lastName2;
	
	@Column(length = 125)
	private String email;
	
	@Column(length = 60)
	private String phone;

	@Column(length = 1)
	private boolean anonymous;	
	
	@Column(length = 1)
	private boolean worker;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	private Set<Candidate> candidates;
	
	//@LazyCollection(LazyCollectionOption.TRUE)
//	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
//	private Set<Suggestion> suggestions;	

	public User() {}
	
	public User(String name, String lastName1, String lastName2, String email, String phone, boolean anonymous, boolean worker) {
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
