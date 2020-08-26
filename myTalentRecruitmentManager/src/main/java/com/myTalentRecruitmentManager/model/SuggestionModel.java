package com.myTalentRecruitmentManager.model;

import java.util.Date;

public class SuggestionModel {
	
	private long id;
	private Date date ;	
	private CustomerModel customer;
	private CandidateModel candidate;

	public SuggestionModel() {}
	
	public SuggestionModel(long id, Date date, CustomerModel customer, CandidateModel candidate) {
		this.id = id;
		this.date = date;
		this.customer = customer;
		this.candidate = candidate;
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
	
	public CustomerModel getCustomer() {
		return customer;
	}

	public void setUser(CustomerModel user) {
		this.customer = user;
	}

	public CandidateModel getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateModel candidate) {
		this.candidate = candidate;
	}

}
