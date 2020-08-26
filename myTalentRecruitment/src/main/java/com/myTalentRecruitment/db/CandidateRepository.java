package com.myTalentRecruitment.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	public Candidate findCandidateById(@Param("id") long id);
	
	@Query("select c from Candidate c") 
	public List<Candidate> findAllCandidates();
	
	@Query("select c from Candidate c where c.email = :email") 
	public Candidate findCandidateByEmail(@Param("email") String email);
	
	@Modifying
	@Transactional
	@Query("update Candidate c set c.fileCVLob = :fileCVLob where c.email = :email") 
	public void updateFileCandidate(@Param("email") String email, @Param("fileCVLob") byte[] fileCVLob);
	
}
