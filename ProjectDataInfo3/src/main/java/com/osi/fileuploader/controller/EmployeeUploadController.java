package com.osi.fileuploader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.osi.fileuploader.pojos.Employee;
import com.osi.fileuploader.service.EmployeeExcelUploadService;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class EmployeeUploadController {

	@Autowired
	private EmployeeExcelUploadService employeeExcelUploadService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadEmployees(@RequestParam("file") MultipartFile file) {
		try {
			if (file.isEmpty()) {
				return ResponseEntity.badRequest().body("File is empty");
			}

			employeeExcelUploadService.uploadEmployeesFromExcel(file);

			return ResponseEntity.ok("File uploaded successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error uploading file: " + e.getMessage());
		}
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployeeNames(@RequestParam String name) {
		List<Employee> employees = employeeExcelUploadService.getEmployeeNames(name);
		return ResponseEntity.ok(employees);
	}

//	@GetMapping("/employee")
//    public ResponseEntity<EmployeeResponseDto> getEmployeeProjectDate(@RequestParam(value = "name") String employeeName) {
//		EmployeeResponseDto responseDto=   employeeExcelUploadService.(employeeName);
//				if (responseDto == null) {
//		            return ResponseEntity.notFound().build();
//		        }
//				return ResponseEntity.ok(responseDto);
//	}
}
