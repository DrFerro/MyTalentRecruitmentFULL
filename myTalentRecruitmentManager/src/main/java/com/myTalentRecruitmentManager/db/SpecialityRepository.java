package com.myTalentRecruitmentManager.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

	@Query("select s.id, s.description from Speciality s")
	public List<Speciality> findSpecialities();
	
	@Query("select s.description from Speciality s where s.id = :id")
	public String findSpecialitydById(@Param("id") long id);
	
	@Query("select s from Speciality s where s.fieldId = :id")
	public List<Speciality> findSpecialitydByField(@Param("id") long id);
	
}
