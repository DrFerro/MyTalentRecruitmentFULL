package com.myTalentRecruitment.model;

import java.util.Date;

public class SuggestionModel {
	
	private long id;
	private Date date ;	
	private UserModel user;
	private CandidateModel candidate;

	public SuggestionModel() {}
	
	public SuggestionModel(long id, Date date, UserModel user, CandidateModel candidate) {
		this.id = id;
		this.date = date;
		this.user= user;
		this.candidate= candidate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public CandidateModel getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateModel candidate) {
		this.candidate = candidate;
	}

}
