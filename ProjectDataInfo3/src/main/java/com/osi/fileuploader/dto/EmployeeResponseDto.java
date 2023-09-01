package com.osi.fileuploader.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {

	private String employeeName;
	private String employeeId;
	private String globalJobcode;
	private String levelandgrade;
	private String reportingManager;
	private String designation;
	private String totalExperience;
	private String joiningExperience;
	private List<String> projectName;
	private List<LocalDate> weekStartDate;
	private List<LocalDate> weekEndDate;
}
