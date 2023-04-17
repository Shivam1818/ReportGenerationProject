package com.shivam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shivam.entities.CitizenPlan;

public interface CitizenRepo extends JpaRepository<CitizenPlan, Integer> {

	 @Query("select distinct(planName) from CitizenPlan") 
	 public List<String> getPlanNames();
	  
	 @Query("select distinct(planStatus) from CitizenPlan") 
	 public List<String> getPlanStatus();
	 
}
