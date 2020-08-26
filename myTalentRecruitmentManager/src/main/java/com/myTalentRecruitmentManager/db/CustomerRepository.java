package com.myTalentRecruitmentManager.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findById(@Param("id") long id);
	
	@Query("select c from Customer c") 
	public List<Customer> findCustomers();
	
//	@Query("select u.email from User u") 
//	public List<User> findAllEmails();
	
	@Query("select u from Customer u where u.email = :email") 
	public Customer findByEmail(@Param("email") String email);
	
//	@Query("INSERT INTO User (apellido1, apellido2, email, nombre, id) VALUES(AES_ENCRYPT((?, ?, ?, ?, ?), 'contraseña')") 
//	public void insertar();
	
}