package com.myTalentRecruitment.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {

	@Query("select c.id, c.name from City c")
	public List<City> findCities();
	
}
