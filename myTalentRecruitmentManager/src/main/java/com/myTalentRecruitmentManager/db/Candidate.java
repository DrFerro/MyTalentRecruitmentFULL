package com.myTalentRecruitmentManager.db;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.myTalentRecruitmentManager.db.City;

@Entity
@Table (name="candidate")
public class Candidate {
	
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
	
	@Column
	private long fieldId;
	
	@Column
	private long specialityId;
	
	@Column
	private long cityId;
	
	@Column(length = 300)
	private String observations;
	
//	@Column(length = 10000)
//	private String fileCV;
	
	@Lob
    @Column(name="fileCVLob")
    private byte[] fileCVLob;
	
	@Column
	private long customerId;
	
	@Column(name="date", columnDefinition="DATE")
	private Date date;
	
//	@Column
//	private boolean viewed;
//	@Column
//	private boolean inTest;
//	@Column
//	private boolean worker;
//	@Column
//	private boolean paid;

	@Column
	private long stateCandidateId;

	@ManyToOne
	@JoinColumn(name = "customerId",insertable=false,updatable=false)	
	private Customer customer;

	@ManyToOne	
	@JoinColumn(name = "fieldId",insertable=false,updatable=false)
	private Field field;
	
	@ManyToOne	
	@JoinColumn(name = "specialityId",insertable=false,updatable=false)
	private Speciality speciality;
	
	@ManyToOne	
	@JoinColumn(name = "cityId",insertable=false,updatable=false)
	private City city;
	
	@ManyToOne	
	@JoinColumn(name = "stateCandidateId",insertable=false,updatable=false)
	private StateCandidate stateCandidate;
	
	public Candidate() {}
	
	public Candidate(String name, String lastName1, String lastName2, String email, String phone, long fieldId, long specialityId, long cityId, String observations, byte[] fileCVLob, long customerId, Date date) {
		this.name = name;
		this.lastName1 = lastName1;
		this.lastName2 = lastName2;
		this.email = email;
		this.phone = phone;
		this.fieldId = fieldId;
		this.specialityId = specialityId;
		this.cityId = cityId;
		this.observations = observations;
		this.fileCVLob = fileCVLob;
		this.customerId = customerId;
		this.date = date;
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

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
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

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public byte[] getFileCV() {
		return fileCVLob;
	}

	public void setFileCV(byte[] fileCVLob) {
		this.fileCVLob = fileCVLob;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public StateCandidate getStateCandidate() {
		return stateCandidate;
	}

	public void setStateCandidate(StateCandidate stateCandidate) {
		this.stateCandidate = stateCandidate;
	}
	
}

