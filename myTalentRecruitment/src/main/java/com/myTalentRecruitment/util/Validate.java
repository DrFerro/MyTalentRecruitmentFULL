package com.myTalentRecruitment.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.myTalentRecruitment.db.User;
import com.myTalentRecruitment.db.UserRepository;
import com.myTalentRecruitment.model.CandidateModel;
import com.myTalentRecruitment.model.UserModel;

public abstract class Validate {

	private static final String PATTERN_NAME = "[A-ZÁÉÍÓÚáéíóúa-z]{1}[A-ZÁÉÍÓÚáéíóúa-z-' ']{0,24}";
	private static final String PATTERN_NAME2 = "[A-ZÁÉÍÓÚáéíóúa-z]{0,1}[A-ZÁÉÍÓÚáéíóúa-z-' ']{0,24}";
	private static final String PATTERN_EMAIL = "[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,3}$";
	private static final String PATTERN_PHONE = "[0-9]{9}";
	private static final String PATTERN_OBSERVATIONS = "[A-ZÁÉÍÓÚáéíóúa-z]{0,1}[A-ZÁÉÍÓÚáéíóúa-z-' '.,\n]{0,149}";
	private static final String PATTERN_WORKER_EMAIL = "[a-z0-9._%+-]+@MyEnterprise.com";

	public static boolean validUser(UserModel userIn) {

		boolean valid = false;

		if(!userIn.isWorker()) {
			
			Pattern pattern = Pattern.compile(PATTERN_NAME);
			Matcher matcherN = pattern.matcher(userIn.getName().trim());
			Matcher matcherLN1 = pattern.matcher(userIn.getLastName1().trim());
			
			pattern = Pattern.compile(PATTERN_NAME2);
			Matcher matcherLN2 = pattern.matcher(userIn.getLastName2().trim());

			pattern = Pattern.compile(PATTERN_EMAIL);
			Matcher matcherE = pattern.matcher(userIn.getEmail().trim());

			pattern = Pattern.compile(PATTERN_PHONE);
			Matcher matcherP = pattern.matcher(userIn.getPhone().trim());

			if(matcherN.matches() && matcherLN1.matches() && matcherLN2.matches() && matcherE.matches() && matcherP.matches())
				valid = true;  	

		} else if(userIn.isWorker()) {		
			Pattern pattern = Pattern.compile(PATTERN_WORKER_EMAIL);
			Matcher matcherE = pattern.matcher(userIn.getEmail().trim());		
			if(matcherE.matches())
				valid = true;  
		}
		
		return valid;
	}
	

	public static boolean validCandidate(CandidateModel candidateIn) {

		boolean valid = false;

		Pattern pattern = Pattern.compile(PATTERN_NAME);
		Matcher matcherN = pattern.matcher(candidateIn.getName().trim());
		Matcher matcherLN1 = pattern.matcher(candidateIn.getLastName1().trim());
		
		pattern = Pattern.compile(PATTERN_NAME2);
		Matcher matcherLN2 = pattern.matcher(candidateIn.getLastName2().trim());

		pattern = Pattern.compile(PATTERN_EMAIL);
		Matcher matcherE = pattern.matcher(candidateIn.getEmail().trim());

		pattern = Pattern.compile(PATTERN_PHONE);
		Matcher matcherP = pattern.matcher(candidateIn.getPhone().trim());
		
		pattern = Pattern.compile(PATTERN_OBSERVATIONS);
		Matcher matcherO = pattern.matcher(candidateIn.getObservations().trim());

		if(matcherN.matches() && matcherLN1.matches() && matcherLN2.matches() && matcherE.matches() && matcherP.matches() && matcherO.matches())
			valid = true;  	

		return valid;
	}

}
