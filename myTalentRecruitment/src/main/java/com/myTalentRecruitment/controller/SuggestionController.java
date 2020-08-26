package com.myTalentRecruitment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myTalentRecruitment.db.City;
import com.myTalentRecruitment.model.FieldModel;
import com.myTalentRecruitment.model.SpecialityModel;
import com.myTalentRecruitment.model.SuggestionModel;
import com.myTalentRecruitment.model.UserModel;
import com.myTalentRecruitment.service.SuggestionService;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {

	private Logger logger = LoggerFactory.getLogger(SuggestionController.class);
	private SuggestionService suggestionService;

	@Autowired
	public SuggestionController(SuggestionService userService) {
		this.suggestionService = userService;
	}	
	
	@RequestMapping(path = "/suggestion", method = RequestMethod.POST)
	boolean createSuggestion(@RequestBody SuggestionModel suggestionIn, @RequestParam("file") MultipartFile file){
		return suggestionService.createSuggestion(suggestionIn);
	}
	
	@PostMapping("/file")
	public ResponseEntity<String> updateFileCandidate(@RequestParam("email") String email, @RequestParam("file") MultipartFile file) {
		String message = "";
		byte[] fileCVLob;
		
		try {		
			fileCVLob = file.getBytes();		
			suggestionService.updateFileCandidate(email, fileCVLob);
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	UserModel createUser(@RequestBody UserModel userIn){
		return suggestionService.createUser(userIn);
	}
	
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	List<UserModel> findUsers(){
		return suggestionService.findAllUsers();
	}
	
	@RequestMapping(path = "/fields", method = RequestMethod.GET)
	List<FieldModel> findFields() {
		return suggestionService.findFields();
	}
	
	@RequestMapping(path = "/specialities", method = RequestMethod.POST)
	List<SpecialityModel> specialitiesByField(@RequestBody FieldModel field){
		return suggestionService.specialitiesByField(field);
	}
	
	@RequestMapping(path = "/cities", method = RequestMethod.GET)
	List<City> findCities() {
		return suggestionService.findCities();
	}
	
}
