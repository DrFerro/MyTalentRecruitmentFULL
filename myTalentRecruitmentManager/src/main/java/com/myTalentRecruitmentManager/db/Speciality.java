package com.myTalentRecruitmentManager.db;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="speciality")
public class Speciality {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	
	@Column(length=60)
	private String description;
	
	@Column
	private long fieldId;

	@OneToMany(mappedBy="speciality", cascade=CascadeType.ALL)
	private Set<Candidate> candidates;
	
	@ManyToOne	
	@JoinColumn(name = "fieldId",insertable=false,updatable=false)
	private Field field;
	
	public Speciality() {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getFieldId() {
		return fieldId;
	}

	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
	}
	
}
