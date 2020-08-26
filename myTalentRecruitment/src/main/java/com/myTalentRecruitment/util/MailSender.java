package com.myTalentRecruitment.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.myTalentRecruitment.model.CandidateModel;

@Component("javaMailSender")
public class MailSender {

	@Autowired
	private JavaMailSender javaMailSender;
		
	CandidateModel candidate = new CandidateModel();

	public void sendEmail(CandidateModel candidateIn) throws Exception{	
		
	   	String messageCandidate =  "<html>"
        		+ "<body>"
        		+ "Hola "+ candidateIn.getName() + " " + candidateIn.getLastName1() + "<br>"
        		
        		+ "<p style='font-weight:bold'>"
        		+ " 1.       Incorporación de datos personales a los ficheros de MyEnterprise."
        		+ "</p>"      		
        		+ "<p>"
        		+ "De conformidad con lo establecido en la Ley Orgánica 15/1999, de 13 de diciembre, de Protección de Datos de Carácter Personal, se informa a todas las personas que se inscriban o presenten su candidatura (en adelante, \"los Candidatos\") a las ofertas de empleo de MyEnterprise, que todos los datos personales facilitados por el Candidato serán incorporados en los ficheros titularidad de MyEnterprise con las finalidades propias de un departamento de recursos humanos, es decir, de la gestión de su candidatura en los distintos procesos de selección."
        		+ "</p>"
        		
        		+ "<p style='font-weight:bold'>"
        		+ " 2.       Consentimiento para el tratamiento"
        		+ "</p>"      		
        		+ "<p>"
        		+ "De conformidad con lo establecido en la Ley Orgánica 15/1999, de 13 de diciembre, de Protección de Datos de Carácter Personal, se informa a todas las personas que se inscriban o presenten su candidatura (en adelante, \"los Candidatos\") a las ofertas de empleo de !company_title, que todos los datos personales facilitados por el Candidato serán incorporados en los ficheros titularidad de MyEnterprise con las finalidades propias de un departamento de recursos humanos, es decir, de la gestión de su candidatura en los distintos procesos de selección."
        		+ "</p>"
        		
        		+ "<p style='font-weight:bold'>"
        		+ " 3.       Ejercicio de derechos"
        		+ "</p>"      		
        		+ "<p>"
        		+ "El Candidato podrá ejercer los derechos de acceso, rectificación, cancelación y oposición previstos en la Ley Orgánica 15/1999, de 13 de diciembre, de Protección de Datos de Carácter Personal, mediante una comunicación escrita dirigida a MyEnterprise, Parque Empresarial Alvento, Calle Vía de los Poblados, 1, 28033 Madrid; o bien mediante correo electrónico dirigido a "     	
        		+ "<a href='mailto:talent.referral@MyEnterprise.com'>talent.referral@MyEnterprise.com.</a>"
    			+ " En ambos casos, el Candidato deberá acompañar una copia de su documento nacional de identidad, pasaporte u otro documento válido que lo identifique."
        		+ "</p>"
        		
        		+ "<a href='www.MyEnterprise.com'>  </a>"
        		+ "<body>"
        		+ "</html>";

	   	//SimpleMailMessage mail = new SimpleMailMessage();
        MimeMessage message = javaMailSender.createMimeMessage();
        //Ponemos true para que permita adjuntar documentos.
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        //mail.setFrom("davidferrom@gmail.com");
        helper.setTo(candidateIn.getEmail());
        helper.setSubject("Enhorabuena! MyEnterprise considera tu candidatura");     
        helper.setText(messageCandidate, true);      
        
        javaMailSender.send(message);		
 
	}	 

}
