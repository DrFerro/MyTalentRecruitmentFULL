package com.myTalentRecruitment.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findById(@Param("id") long id);
	
	@Query("select u from User u") 
	public List<User> findAll();
	
//	@Query("select u.email from User u") 
//	public List<User> findAllEmails();
	
	@Query("select u from User u where u.email = :email") 
	public User findByEmail(@Param("email") String email);
	
//	@Query("INSERT INTO User (apellido1, apellido2, email, nombre, id) VALUES(AES_ENCRYPT((?, ?, ?, ?, ?), 'contraseña')") 
//	public void insertar();
	
}
