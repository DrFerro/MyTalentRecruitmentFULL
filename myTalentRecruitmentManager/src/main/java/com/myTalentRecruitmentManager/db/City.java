package com.myTalentRecruitmentManager.db;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="city")
public class City {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;

	@Column(length=25)
	private String name;

	@OneToMany(mappedBy="city", cascade=CascadeType.ALL)
	private Set<Candidate> candidates;
	
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
	
}
