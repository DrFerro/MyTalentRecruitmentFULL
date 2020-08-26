 package com.myTalentRecruitmentManager.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	
	@Query("select c from Candidate c where c.id = :id") 
	public Candidate findCandidateById(@Param("id") long id);
	
	@Query("select c.fileCVLob from Candidate c where c.id = :id") 
	public byte[] findFile(@Param("id") long id);
	
//	@Modifying
//	@Transactional
//	@Query("update Candidate c set c.observations = :obs, c.viewed = :viewed, c.inTest = :inTest, c.worker = :worker, c.paid = :paid where c.id = :id") 
//	public void updateCandidate(@Param("id") long id, @Param("obs") String obs,  @Param("viewed") Boolean viewed,  @Param("inTest") Boolean inTest);
	
	@Modifying
	@Transactional
	@Query("update Candidate c set c.observations = :obs, c.stateCandidateId = :stateCandidateId where c.id = :id") 
	public void updateCandidate(@Param("id") long id, @Param("obs") String obs, @Param("stateCandidateId") long stateCandidateId);
	
	@Modifying
	@Transactional
	@Query("delete from Candidate c where c.id = :id") 
	public void deleteCandidate(@Param("id") long id);
	
	@Query("select c from Candidate c") 
	public List<Candidate> findAllCandidates();	
	
	@Query("select c from Candidate c where (c.fieldId = :fieldId or :fieldId = 0) and (c.specialityId = :specialityId or :specialityId = 0) and (c.cityId = :cityId or :cityId = 0) and (c.stateCandidateId = :stateCandidateId or :stateCandidateId = 0)")     
	public List<Candidate> seekCandidatesByFilter(@Param("fieldId") long fieldId, @Param("specialityId") long specialityId, @Param("cityId") long cityId, @Param("stateCandidateId") long stateCandidateId);
	
//	 and (c.inTest = :inTest or :inTest = null) and (c.worker = :worker or :worker = null) and (c.paid = :paid or :paid = null)
	
	
//	@Query("select c from Candidate c where c.fieldId = :fieldId") 
//	public List<Candidate> seekCandidatesByField(@Param("fieldId") long fieldId);
//	
//	@Query("select c from Candidate c where c.cityId = :cityId") 
//	public List<Candidate> seekCandidatesByCity(@Param("cityId") long cityId);
//	
//	@Query("select c from Candidate c where c.fieldId = :fieldId and c.specialityId = :specialityId") 
//	public List<Candidate> seekCandidatesByFieldSpeciality(@Param("fieldId") long fieldId, @Param("specialityId") long specialityId);
//	
//	@Query("select c from Candidate c where c.fieldId = :fieldId and c.cityId = :cityId") 
//	public List<Candidate> seekCandidatesByFieldCity(@Param("fieldId") long fieldId, @Param("cityId") long cityId);
//	
//	@Query("select c from Candidate c where c.fieldId = :fieldId and c.specialityId = :specialityId and c.cityId = :cityId") 
//	public List<Candidate> seekCandidatesByFieldSpecialityCity(@Param("fieldId") long fieldId, @Param("specialityId") long specialityId, @Param("cityId") long cityId);
	
	@Query("select c from Candidate c where c.email = :email") 
	public Candidate findCandidateByEmail(@Param("email") String email);
	
}
