package com.osi.fileuploader.pojos;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EmployeeProject {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String activity;
	private String approverHist;
	private String deliveryManager;
	private String employee;
	private String empBU;
	private String empOrganisation;
	private String empPractice;
	private String empregion;
	private String empStrategicAccount;
	private String empSubPractice;
	private String empXferEntity;
	private String month;
	//private String notes;
	private String project;
	private Timestamp weekEndDate;
	private Timestamp weekStartDate;
	
}
