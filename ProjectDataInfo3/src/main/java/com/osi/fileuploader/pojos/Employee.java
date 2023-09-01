package com.osi.fileuploader.pojos;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	
	/*
	 * private String employeeId; private String employeeName; private String
	 * globalJobcode; private String levelandgrade; private String reportingManager;
	 * private String designation; private String totalExperience; private String
	 * joiningExperience;
	 */
	
	private String bloodGroup;
	private String BU;
	private String contractType;
	private String dateOfBirth;
	private String  dateOfExit;
	private String dateOfJoining;
	private String department;
	private String designation;
	private String employeeFullName;
	private String employeeId;
	private String employeeName;
	private String employeeStatus;
	private String entity;
	private String gender;
	private String globalJobCode;
	private String joiningExperience;
	private String levelAndGrade;
	private String location;
	private String martialStatus;
	private String mobileNumber;
	private String OSIEmailId;
	private String reportingManager;
	private String RMEmailId;
	private String strategic;
	private String subPractice;
	private String typeOfEmployment;
	private String userName;
	
	

//	@OneToMany
//	private List<EmployeeProject> employeeProject;
	
	/*
	 * Blood Group BU Contract Type Date Of Birth Date Of Exit Date Of Joining
	 * Department Designation Employee Full Name Employee ID Employee Name Employee
	 * Status Entity First Name Gender Global Job Code Joining Experience Joining
	 * Experience Days Joining Experience Months Last Name Level and Grade Location
	 * Marital Status Middle Name Mobile Number On Probation OSI Email ID OSI
	 * Experience OSI Experience Days OSI Experience Months Personal Email Practice
	 * Region Reporting Manager RM Email ID RM EMP ID Strategic Acccount Sub
	 * Practice Total Experience Total Experience Days Total Experience Months Type
	 * of Employment User Name Xfer Entity
	 * 
	 */
}
