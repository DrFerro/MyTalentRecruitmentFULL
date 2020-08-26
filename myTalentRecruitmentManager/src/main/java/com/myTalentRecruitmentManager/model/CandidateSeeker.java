package com.myTalentRecruitmentManager.model;

public class CandidateSeeker {
	
	private long fieldId;
	private long specialityId;
	private long cityId;
//	private Boolean viewed = null;
//	private boolean inTest;
//	private boolean worker;
//	private boolean paid;
	private long stateCandidateId;

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
//	public boolean isViewed() {
//		return viewed;
//	}
//	public void setViewed(boolean viewed) {
//		this.viewed = viewed;
//	}
//	public boolean isInTest() {
//		return inTest;
//	}
//	public void setInTest(boolean inTest) {
//		this.inTest = inTest;
//	}
//	public boolean isWorker() {
//		return worker;
//	}
//	public void setWorker(boolean worker) {
//		this.worker = worker;
//	}
//	public boolean isPaid() {
//		return paid;
//	}
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
