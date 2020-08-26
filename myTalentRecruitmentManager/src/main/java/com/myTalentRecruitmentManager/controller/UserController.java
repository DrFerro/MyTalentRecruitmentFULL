package com.myTalentRecruitmentManager.controller;


import com.myTalentRecruitmentManager.db.Candidate;
import com.myTalentRecruitmentManager.db.City;
import com.myTalentRecruitmentManager.db.Field;
import com.myTalentRecruitmentManager.db.Speciality;
import com.myTalentRecruitmentManager.db.StateCandidate;
import com.myTalentRecruitmentManager.db.User;
import com.myTalentRecruitmentManager.model.CandidateModel;
import com.myTalentRecruitmentManager.model.CandidateSeeker;
import com.myTalentRecruitmentManager.model.CustomerModel;
import com.myTalentRecruitmentManager.model.LoginUserComplete;
import com.myTalentRecruitmentManager.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/talentmanager")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody LoginUserComplete user){
        return userService.save(user);
    }
    
    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.POST)
    public void deleteUser(@PathVariable(value = "id") Long id){
        userService.delete(id);
    }
    
    @RequestMapping(value="/customers", method = RequestMethod.GET)
    public List<CustomerModel> listCustomers(){
        return userService.allCustomers();
    }

    @RequestMapping(value="/candidates", method = RequestMethod.GET)
    public List<Candidate> findCandidates(){
        return userService.findCandidates();
    }
    
	@RequestMapping(path = "/seekCandidates", method = RequestMethod.POST)
	List<Candidate> seekCandidates(@RequestBody CandidateSeeker candidateSeeker) {
		return userService.seekCandidates(candidateSeeker);
	}
    
    @RequestMapping(value = "/candidate/{id}", method = RequestMethod.GET)
    public Candidate getCandidate(@PathVariable(value = "id") Long id){
        return userService.findCandidateById(id);
    }
    
//	@GetMapping("/file/{id}")
//	@ResponseBody
    @RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
	public byte[] getFile(@PathVariable(value = "id") Long id) {		
		return userService.downloadFile(id);			
	}  
    
//	@GetMapping("/file/{id}")
//	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
//		Optional<FileModel> fileOptional = fileRepository.findById(id);
//		
//		if(fileOptional.isPresent()) {
//			FileModel file = fileOptional.get();
//			return ResponseEntity.ok()
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
//					.body(file.getPic());	
//		}
//		
//		return ResponseEntity.status(404).body(null);
//	}
    
    @RequestMapping(value = "/updateCandidate", method = RequestMethod.POST)
    public void updateCandidate(@RequestBody CandidateModel candidate){
        userService.updateCandidate(candidate);
    }
    
    @RequestMapping(value = "/deleteCandidate/{id}", method = RequestMethod.POST)
    public void deleteCandidate(@PathVariable(value = "id") Long id){
        userService.deleteCandidate(id);
    }
    
	@RequestMapping(path = "/fields", method = RequestMethod.GET)
	public List<Field> findFields() {
		return userService.findFields();
	}
	
    @RequestMapping(value="/field", method = RequestMethod.POST)
    public Field saveField(@RequestBody Field field){
        return userService.saveField(field);
    }
	
	@RequestMapping(path = "/specialities/{id}", method = RequestMethod.GET)
	public List<Speciality> specialitiesByField(@PathVariable(value = "id") Long id){
		return userService.specialitiesByField(id);
	}
	
    @RequestMapping(value="/speciality", method = RequestMethod.POST)
    public Speciality saveSpeciality(@RequestBody Speciality speciality){
        return userService.saveSpeciality(speciality);
    }
    
	@RequestMapping(path = "/cities", method = RequestMethod.GET)
	public List<City> findCities() {
		return userService.findCities();
	}
	
	@RequestMapping(path = "/statesCandidate", method = RequestMethod.GET)
	public List<StateCandidate> findStatesCandidate() {
		return userService.findStatesCandidate();
	}

}
