package com.myTalentRecruitment.service;

import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myTalentRecruitment.db.Candidate;
import com.myTalentRecruitment.db.CandidateRepository;
import com.myTalentRecruitment.db.City;
import com.myTalentRecruitment.db.CityRepository;
import com.myTalentRecruitment.db.Field;
import com.myTalentRecruitment.db.FieldRepository;
import com.myTalentRecruitment.db.Speciality;
import com.myTalentRecruitment.db.SpecialityRepository;
import com.myTalentRecruitment.db.StateCandidate;
import com.myTalentRecruitment.db.StateCandidateRepository;
import com.myTalentRecruitment.db.Suggestion;
import com.myTalentRecruitment.db.SuggestionRepository;
import com.myTalentRecruitment.db.User;
import com.myTalentRecruitment.db.UserRepository;
import com.myTalentRecruitment.exception.MyTalentRecruitmentException;
import com.myTalentRecruitment.model.CandidateModel;
import com.myTalentRecruitment.model.FieldModel;
import com.myTalentRecruitment.model.SpecialityModel;
import com.myTalentRecruitment.model.SuggestionModel;
import com.myTalentRecruitment.model.UserModel;
import com.myTalentRecruitment.util.Conv;
import com.myTalentRecruitment.util.MailSender;
import com.myTalentRecruitment.util.Validate;

@Service
public class SuggestionService {
	
	/***************************** Metodos relacionados con Suggestion *************************************************************/
	
	@Autowired
    @Qualifier("javaMailSender")
	public MailSender mailSender;
	
	public boolean createSuggestion (SuggestionModel suggestionIn) {
		
		boolean created = false;		
		CandidateModel candidateOut = new CandidateModel();
		
		candidateOut = createCandidate(suggestionIn);
				
		if(candidateOut.getId() != 0) {										
			try {
				mailSender.sendEmail(candidateOut);			
			}  catch (Exception e) {
				throw new MyTalentRecruitmentException(e);
			}  			
		}			
		
		return created;
	}	
	
	/***************************** Metodos relacionados con Candidate *************************************************************/

	@Autowired
	CandidateRepository candidateRepository;

	public CandidateModel createCandidate (SuggestionModel suggestionIn) {
		
		//SuggestionModel suggestionOut = new SuggestionModel();		

		CandidateModel candidateOut = new CandidateModel();
		String name = "", lastName1 ="", lastName2 ="", email="", phone="", observations=""; 
		long fieldId=0, specialityId=0, cityId=0;
		
		//MultipartFile fileCV;
		byte[] fileCVLob;
		
		CandidateModel candidateIn = new CandidateModel(suggestionIn.getCandidate().getName(), suggestionIn.getCandidate().getLastName1(),
				suggestionIn.getCandidate().getLastName2(), suggestionIn.getCandidate().getEmail(),
				suggestionIn.getCandidate().getPhone(), suggestionIn.getCandidate().getFieldId(),
				suggestionIn.getCandidate().getSpecialityId(), suggestionIn.getCandidate().getCityId(), suggestionIn.getCandidate().getObservations(), 1L);	
		
		Candidate existCandidate = findCandidateByEmail(candidateIn.getEmail());
		
		if(existCandidate.getId()==0) {	
			
			if(Validate.validCandidate(candidateIn)) {			
				try {	
					
					UserModel userIn = new UserModel(suggestionIn.getUser().getName(), suggestionIn.getUser().getLastName1(),
							 suggestionIn.getUser().getLastName2(), suggestionIn.getUser().getEmail(),
							 suggestionIn.getUser().getPhone(), suggestionIn.getUser().isAnonymous(),
							 suggestionIn.getUser().isWorker());	
					UserModel userOut = createUser(userIn);					
					
					Candidate candidateDb = new Candidate();			
					name = Conv.encrypt(candidateIn.getName());
					lastName1 = Conv.encrypt(candidateIn.getLastName1());
					lastName2 = Conv.encrypt(candidateIn.getLastName2());
					email = Conv.encrypt(candidateIn.getEmail());
					phone = Conv.encrypt(candidateIn.getPhone());
					fieldId = candidateIn.getFieldId();
					cityId = candidateIn.getCityId();
					
					//Si el id de la categoría es 4(Otros) entonces el id de la especialidad es 10(Otros).
					if(candidateIn.getFieldId() == 4){
						specialityId = 10;
					} else {
						specialityId = candidateIn.getSpecialityId();
					}		
					
					observations = candidateIn.getObservations();			

					candidateDb = new Candidate(name, lastName1, lastName2, email, phone, fieldId, specialityId, cityId, observations, userOut.getId(), new Timestamp(new Date().getTime()), 1L);		
					candidateRepository.save(candidateDb);
					candidateOut = new CandidateModel(candidateDb.getId(), candidateDb.getName(), candidateDb.getLastName1(), candidateDb.getLastName2(), candidateDb.getEmail(), candidateDb.getPhone(), candidateDb.getFieldId(), candidateDb.getSpecialityId(), candidateDb.getCityId(), candidateDb.getObservations(), candidateDb.getCustomerId(), candidateDb.getDate(), candidateDb.getStateCandidateId());

//					Suggestion suggestionBd = new Suggestion(new Timestamp(new Date().getTime()), userOut.getId(), candidateOut.getId());			
//					suggestionRepository.save(suggestionBd);			
//					suggestionOut = new SuggestionModel(suggestionBd.getId(), suggestionBd.getDate(), userOut, candidateOut);					
					
				} catch (Exception e) {
					throw new MyTalentRecruitmentException("Error al insertar el candidato en la base de datos");
				}		
				
			} else {
				throw new MyTalentRecruitmentException("Los datos del candidato deben venir bien formados.");
			}		
		}
		
		return candidateOut;
	}
	
	public void updateFileCandidate(String email, byte[] fileCVLob) {
		
		System.out.println(email+" eeeeeeeeeeeee "+fileCVLob);
		
		candidateRepository.updateFileCandidate(email, fileCVLob);
	}
	
	public Candidate findCandidateByEmail(String email) {	
		
		Candidate candidate = new Candidate();
		Candidate candidateOut = new Candidate();
		List<Candidate> list = candidateRepository.findAll();
		
		if(!list.isEmpty()){	
			Iterator<Candidate> itr = list.iterator();
			while(itr.hasNext()){
				candidate = itr.next();
				if( Conv.decrypt(candidate.getEmail()).equals(email) )
					candidateOut = candidate;					
			}			
		}
		
		return candidateOut;
	}
	
	/***************************** Metodos relacionados con Field *************************************************************/

	@Autowired
	FieldRepository fieldRepository;
	
	public List<FieldModel> findFields() {

		List<Field> fieldsBd = new ArrayList<Field>();
		fieldsBd = fieldRepository.findFields();

		Iterator itr = fieldsBd.iterator();
		List<FieldModel> fieldsOut = new ArrayList<FieldModel>();

		while (itr.hasNext()) {
			FieldModel fieldIn = new FieldModel();
			Object[] obj = (Object[]) itr.next();
			fieldIn.setId(Long.parseLong(String.valueOf(obj[0])));
			fieldIn.setDescription(String.valueOf(obj[1]));
			fieldsOut.add(fieldIn);
		}

		return fieldsOut;
	}
	
	/***************************** Metodos relacionados con Speciality *************************************************************/

	@Autowired
	SpecialityRepository specialityRepository;
	
	public List<SpecialityModel> specialitiesByField(FieldModel field) {

		List<Speciality> specialitiesBd = new ArrayList<Speciality>();
		specialitiesBd = specialityRepository.findSpecialitydByField(field.getId());

		Iterator itr = specialitiesBd.iterator();
		List<SpecialityModel> specialitiesOut = new ArrayList<SpecialityModel>();

		while (itr.hasNext()) {
			SpecialityModel specialityIn = new SpecialityModel();
			Object[] obj = (Object[]) itr.next();
			specialityIn.setId(Long.parseLong(String.valueOf(obj[0])));
			specialityIn.setDescription(String.valueOf(obj[1]));
			specialitiesOut.add(specialityIn);
		}

		return specialitiesOut;
	}
	
	/***************************** Metodos relacionados con StateCandidate *************************************************************/

	@Autowired
	StateCandidateRepository stateCandidateRepository;
	
	/***************************** Metodos relacionados con City *************************************************************/

	@Autowired
	CityRepository cityRepository;
	
	public List<City> findCities() {
		List<City> list = new ArrayList<>();
		cityRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	/***************************** Metodos relacionados con User *************************************************************/
	@Autowired
	UserRepository userRepository;

	public UserModel createUser (UserModel userIn) {

		UserModel userOut = new UserModel();
		String name = "", lastName1 ="", lastName2 ="", email="", phone="";	
		
		User existUser = findUserByEmail(userIn.getEmail());
	
		if(existUser.getId()==0) {
			
			if(userIn.isWorker()) {
				User userDb = new User();			
				email = Conv.encrypt(userIn.getEmail());
				userDb = new User("worker", "", "", email, "", userIn.isAnonymous(), userIn.isWorker());		
				userRepository.save(userDb);
				userOut = new UserModel(userDb.getId(), userDb.getName(), userDb.getLastName1(), userDb.getLastName2(), userIn.getEmail(), userDb.getPhone(), userIn.isAnonymous(),userIn.isWorker());
			
			} else {			
				if(Validate.validUser(userIn)) {				
					try {	
						
						User userDb = new User();			
						name = Conv.encrypt(userIn.getName());
						lastName1 = Conv.encrypt(userIn.getLastName1());
						lastName2 = Conv.encrypt(userIn.getLastName2());
						email = Conv.encrypt(userIn.getEmail());
						phone = Conv.encrypt(userIn.getPhone());

						userDb = new User(name, lastName1, lastName2, email, phone, userIn.isAnonymous(), userIn.isWorker());		
						userRepository.save(userDb);
						userOut = new UserModel(userDb.getId(), userIn.getName(), userIn.getLastName1(), userIn.getLastName2(), userIn.getEmail(), userIn.getPhone(), userIn.isAnonymous(),userIn.isWorker());

					} catch (Exception e) {
						throw new MyTalentRecruitmentException("Error al insertar el usuario en la base de datos");
					}	
					
				} else {
					throw new MyTalentRecruitmentException("Los datos del usuario deben venir bien formados.");
				}					
			}		

		} else {			
			userOut = new UserModel(existUser.getId(), Conv.decrypt(existUser.getName()),
									Conv.decrypt(existUser.getLastName1()), Conv.decrypt(existUser.getLastName2()),
									Conv.decrypt(existUser.getEmail()), Conv.decrypt(existUser.getPhone()),existUser.isAnonymous(),existUser.isWorker());			
		}

		return userOut;
	}		
	
	public User findUserByEmail(String email) {	
		
		User user = new User();
		User userOut = new User();
		List<User> list = userRepository.findAll();
		
		if(!list.isEmpty()){	
			
			Iterator<User> itr = list.iterator();
			while(itr.hasNext()){
				user = itr.next();
				if( Conv.decrypt(user.getEmail()).equals(email) )
					userOut = user;					
			}			
		}
		
		return userOut;
	}

	public List<UserModel> findAllUsers () {

		List<User> list = userRepository.findAll();
		List<UserModel> listOut = new ArrayList<UserModel>();

		if(!list.isEmpty()){	

			User user = new User();
			UserModel userOut = new UserModel();
			String name = "", lastName1 = "", lastName2 = "", email= "", phone= "";
			long id;
			boolean anonymous = false, worker = false;
			Iterator<User> itr = list.iterator();

			while(itr.hasNext()){
				user = itr.next();

				id = user.getId();
				name = Conv.decrypt(user.getName());
				lastName1 = Conv.decrypt(user.getLastName1());
				lastName2 = Conv.decrypt(user.getLastName2());
				email = Conv.decrypt(user.getEmail());
				phone = Conv.decrypt(user.getPhone());
				anonymous = user.isAnonymous();
				worker = user.isWorker();

				userOut = new UserModel(id, name, lastName1, lastName2, email, phone, anonymous, worker);
				listOut.add(userOut);
			}
		}	

		return listOut;
	}
	

}
