package com.myTalentRecruitmentManager.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
//@Table (name="suggestion")
public class Suggestion {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column
//	private long id;
//
//	@Column(name="date", columnDefinition="DATE")
//	private Date date;	
//
//	@Column
//	private long userId;
//
//	@Column
//	private long candidateId;
//	
//	@ManyToOne
//	@JoinColumn(name = "userId",insertable=false,updatable=false)	
//	private User user;
//	
//	@OneToOne
//	@JoinColumn(name = "candidateId",insertable=false,updatable=false)	
//	private Candidate candidate;
//
//	public Suggestion() {}
//	
//	public Suggestion(Date date, long userId,  long candidateId) {
//		this.date = date;
//		this.userId = userId;
//		this.candidateId = candidateId;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//	public long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

}
