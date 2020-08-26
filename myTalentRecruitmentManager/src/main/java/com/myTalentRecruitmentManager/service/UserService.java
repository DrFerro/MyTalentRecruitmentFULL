package com.myTalentRecruitmentManager.service;

import com.myTalentRecruitmentManager.db.Candidate;
import com.myTalentRecruitmentManager.db.City;
import com.myTalentRecruitmentManager.db.Field;
import com.myTalentRecruitmentManager.db.Speciality;
import com.myTalentRecruitmentManager.db.StateCandidate;
//import com.myTalentRecruitmentManager.db.Customer;
import com.myTalentRecruitmentManager.db.User;
import com.myTalentRecruitmentManager.model.CandidateModel;
import com.myTalentRecruitmentManager.model.CandidateSeeker;
import com.myTalentRecruitmentManager.model.CustomerModel;
import com.myTalentRecruitmentManager.model.LoginUserComplete;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.core.io.Resource;

public interface UserService {

	User save(LoginUserComplete user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);
    User findById(Long id);
    
    Candidate findCandidateById(Long id);    
    void updateCandidate(CandidateModel candidate);
    void deleteCandidate(Long id);
    List<Candidate> findCandidates();
    List<Candidate> seekCandidates(CandidateSeeker candidateSeeker);
    byte[] downloadFile(Long id);
    
    List<Field> findFields();
    Field saveField(Field field);

    List<Speciality> specialitiesByField(Long id);
    Speciality saveSpeciality(Speciality speciality);
    
    List<City> findCities();
    
    List<StateCandidate> findStatesCandidate();
    
    List<CustomerModel> allCustomers();
    
}
