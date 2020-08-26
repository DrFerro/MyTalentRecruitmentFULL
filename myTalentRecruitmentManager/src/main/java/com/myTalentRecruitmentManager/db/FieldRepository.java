package com.myTalentRecruitmentManager.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FieldRepository extends JpaRepository<Field, Long> {

	@Query("select f from Field f")
	public List<Field> findFields();
	
	@Query("select f.description from Field f where f.id = :id")
	public String findFieldById(@Param("id") long id);
	
}
