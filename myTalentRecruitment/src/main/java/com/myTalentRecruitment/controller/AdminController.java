package com.myTalentRecruitment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myTalentRecruitment.service.AdminService;

@RestController
@RequestMapping("/UrlAdmin")

public class AdminController {

	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	private AdminService adminService;

	@Autowired
	public AdminController(AdminService service) {
		this.adminService = service;
	}
	
	@RequestMapping(path = "/saluda", method = RequestMethod.GET)
	String nombreDelMetodo() {		
		return adminService.saluda();
	}
	
}