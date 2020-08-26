package com.myTalentRecruitmentManager.service;

import com.myTalentRecruitmentManager.db.Candidate;
import com.myTalentRecruitmentManager.db.CandidateRepository;
import com.myTalentRecruitmentManager.db.City;
import com.myTalentRecruitmentManager.db.CityRepository;
import com.myTalentRecruitmentManager.db.Customer;
import com.myTalentRecruitmentManager.db.CustomerRepository;
import com.myTalentRecruitmentManager.db.Field;
import com.myTalentRecruitmentManager.db.FieldRepository;
import com.myTalentRecruitmentManager.db.Speciality;
import com.myTalentRecruitmentManager.db.SpecialityRepository;
import com.myTalentRecruitmentManager.db.StateCandidate;
import com.myTalentRecruitmentManager.db.StateCandidateRepository;
import com.myTalentRecruitmentManager.db.User;
import com.myTalentRecruitmentManager.db.UserRepository;
import com.myTalentRecruitmentManager.model.LoginUserModel;
import com.myTalentRecruitmentManager.model.CandidateModel;
import com.myTalentRecruitmentManager.model.CandidateSeeker;
import com.myTalentRecruitmentManager.model.CustomerModel;
import com.myTalentRecruitmentManager.model.LoginUserComplete;
import com.myTalentRecruitmentManager.service.UserService;
import com.myTalentRecruitmentManager.util.Conv;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	CandidateRepository candidateRepository;
	
	public List<Candidate> findCandidates() {
		List<Candidate> list = new ArrayList<>();
		candidateRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	public List<Candidate> seekCandidates(CandidateSeeker candidateSeeker) {
		List<Candidate> list = new ArrayList<>();
		candidateRepository.seekCandidatesByFilter(candidateSeeker.getFieldId(), candidateSeeker.getSpecialityId(), candidateSeeker.getCityId(), candidateSeeker.getStateCandidateId()).iterator().forEachRemaining(list::add);
		return list;
	}
	
	public Candidate findCandidateById(Long id) {
		Candidate candidate = new Candidate();
		candidate = candidateRepository.findCandidateById(id);				
		return candidate;
	}
	
	public byte[] downloadFile(Long id) {
		
		byte[] arrayFile;	
		try {			
			arrayFile = candidateRepository.findFile(id);					
			System.out.println("Fichero " + id + " descargado" + arrayFile);
							
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}		
		return arrayFile;		
	}
	
	public void updateCandidate(CandidateModel candidate) {
		candidateRepository.updateCandidate(candidate.getId(), candidate.getObservations(), candidate.getStateCandidateId());
	}	
	
	public void deleteCandidate(Long id) {
		candidateRepository.deleteCandidate(id);
	}
	
	@Autowired
	FieldRepository fieldRepository;
	
	public List<Field> findFields() {
		List<Field> list = new ArrayList<>();
		fieldRepository.findFields().iterator().forEachRemaining(list::add);
		return list;
	}
	
	public Field saveField(Field fieldIn) {
		Field newField = new Field();
		newField.setDescription(fieldIn.getDescription());	
		return fieldRepository.save(newField);
	}
	
	@Autowired
	SpecialityRepository specialityRepository;
	
	public List<Speciality> specialitiesByField(Long id) {
		List<Speciality> list = new ArrayList<>();
		specialityRepository.findSpecialitydByField(id).iterator().forEachRemaining(list::add);
		return list;
	}
	
	public Speciality saveSpeciality(Speciality specialityIn) {
		Speciality newSpeciality = new Speciality();
		newSpeciality.setDescription(specialityIn.getDescription());
		newSpeciality.setFieldId(specialityIn.getFieldId());
		return specialityRepository.save(newSpeciality);
	}
	
	@Autowired
	CityRepository cityRepository;
	
	public List<City> findCities() {
		List<City> list = new ArrayList<>();
		cityRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Autowired
	StateCandidateRepository stateCandidateRepository;
	
	public List<StateCandidate> findStatesCandidate() {
		List<StateCandidate> list = new ArrayList<>();
		stateCandidateRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	
	@Autowired
	CustomerRepository customerRepository;

	public List<CustomerModel> allCustomers () {

		List<Customer> list = customerRepository.findAll();
		List<CustomerModel> listOut = new ArrayList<CustomerModel>();

		if(!list.isEmpty()){	

			Customer user = new Customer();
			CustomerModel userOut = new CustomerModel();
			String name = "", lastName1 = "", lastName2 = "", email= "", phone= "";
			long id;
			boolean anonymous = false, worker = false;
			Iterator<Customer> itr = list.iterator();

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

				userOut = new CustomerModel(id, name, lastName1, lastName2, email, phone, anonymous, worker);
				listOut.add(userOut);
			}
		}	

		return listOut;
	}
	
	@Autowired
	private UserRepository userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findOne(id);
	}

	@Override
    public User save(LoginUserComplete user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }
}
