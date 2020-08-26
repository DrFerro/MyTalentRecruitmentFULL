package com.myTalentRecruitment.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class CandidateModel {

	private long id;
	private String name;
	private String lastName1;
	private String lastName2;
	private String email;
	private String phone;
	private long fieldId;
	private long specialityId;
	private long cityId;
	private String observations;
	private long userId;
	private Date date;
	private MultipartFile fileCV;
//	private boolean viewed;
//	private boolean inTest;
//	private boolean worker;
//	private boolean paid;
	private long stateCandidateId;


	public CandidateModel() {}
	
	public CandidateModel(String name, String lastName1, String lastName2, String email, String phone, long fieldId, long specialityId, long cityId, String observations, long stateCandidateId) {
		this.name = name;
		this.lastName1 = lastName1;
		this.lastName2 = lastName2;
		this.email = email;
		this.phone = phone;
		this.fieldId = fieldId;
		this.specialityId = specialityId;
		this.cityId = cityId;
		this.observations = observations;
		this.stateCandidateId = stateCandidateId;

	}
	
	/* Constructor para el candidato que viene desde el front */
	public CandidateModel(String name, String lastName1, String lastName2, String email, String phone, long fieldId, long specialityId, long cityId, String observations, MultipartFile fileCV) {
		this.name = name;
		this.lastName1 = lastName1;
		this.lastName2 = lastName2;
		this.email = email;
		this.phone = phone;
		this.fieldId = fieldId;
		this.specialityId = specialityId;
		this.cityId = cityId;
		this.observations = observations;
		this.fileCV = fileCV;
	}
	
	/* Constructor para el candidato de salida hacia el front */
	public CandidateModel(long id, String name, String lastName1, String lastName2, String email, String phone, long fieldId, long specialityId, long cityId, String observations, long userId, Date date, long stateCandidateId) {
		this.id = id;
		this.name = name;
		this.lastName1 = lastName1;
		this.lastName2 = lastName2;
		this.email = email;
		this.phone = phone;
		this.fieldId = fieldId;
		this.specialityId = specialityId;
		this.cityId = cityId;
		this.observations = observations;
		this.userId = userId;
		this.date = date;
		this.stateCandidateId = stateCandidateId;
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
	public long getFieldId() {
		return fieldId;
	}
	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
	}
	public long getSpecialityId() {
		return specialityId;
	}
	public void setSpecialityId(long specialityId) {
		this.specialityId = specialityId;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public MultipartFile getFileCV() {
		return fileCV;
	}
	public void setFileCV(MultipartFile fileCV) {
		this.fileCV = fileCV;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
//	public boolean isViewed() {
//		return viewed;
//	}
//
//	public void setViewed(boolean viewed) {
//		this.viewed = viewed;
//	}
//
//	public boolean isInTest() {
//		return inTest;
//	}
//
//	public void setInTest(boolean inTest) {
//		this.inTest = inTest;
//	}
//
//	public boolean isWorker() {
//		return worker;
//	}
//
//	public void setWorker(boolean worker) {
//		this.worker = worker;
//	}
//
//	public boolean isPaid() {
//		return paid;
//	}
//
//	public void setPaid(boolean paid) {
//		this.paid = paid;
//	}
	
	public long getStateCandidateId() {
		return stateCandidateId;
	}

	public void setStateCandidateId(long stateCandidateId) {
		this.stateCandidateId = stateCandidateId;
	}
	
}
