package com.myTalentRecruitment.exception;

public class MyTalentRecruitmentException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MyTalentRecruitmentException() {}

	public MyTalentRecruitmentException(String arg0) {
		super(arg0);
	}

	public MyTalentRecruitmentException(Throwable arg0) {
		super(arg0);
	}

	public MyTalentRecruitmentException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MyTalentRecruitmentException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
