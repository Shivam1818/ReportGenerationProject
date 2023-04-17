package com.shivam.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CITIZEN_PLAN_INFO")
@Data
public class CitizenPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenId;
	private String citizenName;
	private String gender;
	private String planName;
	private String planStatus;
	private String denialReason;
	private String terminationRsn;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private LocalDate terminationDate;
	private Double benefitAmt;
	

}
