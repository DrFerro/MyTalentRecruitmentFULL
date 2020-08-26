package com.myTalentRecruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Imports necesarios para autorun en el despliegue.
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MyTalentRecruitmentApplication extends SpringBootServletInitializer {
	
	//Método necesario para autorun en el despliegue.
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyTalentRecruitmentApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MyTalentRecruitmentApplication.class, args);
	}
}
