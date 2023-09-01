package com.osi.fileuploader.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.osi.fileuploader.dto.EmployeeRepoDto;
import com.osi.fileuploader.dto.EmployeeResponseDto;
import com.osi.fileuploader.repository.EmployeeProjectRepo;
import com.osi.fileuploader.service.EmployeeExcelUploadService;
import com.osi.fileuploader.service.EmployeeProjectExcelUploadService;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class EmployeeProjectUploadController {

	@Autowired
	private EmployeeProjectExcelUploadService employeeProjectExcelUploadService;

	@Autowired
	EmployeeExcelUploadService employeeExcelUploadService;
	@Autowired
	private EmployeeProjectRepo employeeProjectRepo;

	@GetMapping("/repo")
	public EmployeeResponseDto getProject(@RequestParam String employeeName) {

		EmployeeResponseDto emp = employeeExcelUploadService.getEmployeeProjectData(employeeName);
//		List<Object[]> list = employeeProjectRepo.findProjectDetailsByEmployeeContaining(employeeName);
//		List<EmployeeRepoDto> dtos = new ArrayList<EmployeeRepoDto>();
//
//		for (Object[] result : list) {
//			EmployeeRepoDto dto = new EmployeeRepoDto();
//			dto.setProject(result[0].toString());
//			dto.setWeekStartDate(result[1].toString());
//			dto.setWeekEndDate(result[2].toString());
//			dtos.add(dto);
//		}

		return emp;
	}

	@PostMapping("/upload-excel")
	public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
		try {
			if (file.isEmpty()) {
				return ResponseEntity.badRequest().body("File is empty");
			}
			    
				employeeProjectExcelUploadService.processExcelFile(file);

			return ResponseEntity.ok("File uploaded successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error uploading file: " + e.getMessage());
		}
	}
	
	
	

}
