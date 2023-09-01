package com.osi.fileuploader.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.osi.fileuploader.dto.EmployeeRepoDto;
import com.osi.fileuploader.dto.EmployeeResponseDto;
import com.osi.fileuploader.pojos.Employee;
import com.osi.fileuploader.repository.EmployeeProjectRepo;
import com.osi.fileuploader.repository.EmployeeRepo;

@Service
public class EmployeeExcelUploadService {

	@Autowired
	private EmployeeRepo employeeRepository;

	@Autowired
	private EmployeeProjectRepo employeeProjectRepo;

	public List<Employee> uploadEmployeesFromExcel(MultipartFile file) throws IOException {
		List<Employee> uploadedEmployees = new ArrayList<>();
		 employeeRepository.deleteAll();
		try (InputStream inputStream = file.getInputStream()) {
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next(); // Skip header row

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Employee employee = createEmployeeFromRow(row);
				uploadedEmployees.add(employee);
			}
		}
             
              return employeeRepository.saveAll(uploadedEmployees);
	}

	private Employee createEmployeeFromRow(Row row) {
		Employee employee = new Employee();

		employee.setBloodGroup(getStringCellValue(row.getCell(0)));
		System.out.println(getStringCellValue(row.getCell(0)));
		employee.setBU(getStringCellValue(row.getCell(1)));
		employee.setContractType(getStringCellValue(row.getCell(2)));
		employee.setDateOfBirth(getStringCellValue(row.getCell(3)));
		employee.setDateOfExit(getStringCellValue(row.getCell(4)));
		employee.setDateOfJoining(getStringCellValue(row.getCell(5)));
		employee.setDepartment(getStringCellValue(row.getCell(6)));
		employee.setDesignation(getStringCellValue(row.getCell(7)));
		employee.setEmployeeFullName(getStringCellValue(row.getCell(8)));
		employee.setEmployeeId(getStringCellValue(row.getCell(9)));
		employee.setEmployeeName(getStringCellValue(row.getCell(10)));
		employee.setEmployeeStatus(getStringCellValue(row.getCell(11)));
		employee.setEntity(getStringCellValue(row.getCell(12)));
		employee.setGender(getStringCellValue(row.getCell(14)));
		employee.setGlobalJobCode(getStringCellValue(row.getCell(15)));
		employee.setJoiningExperience(getStringCellValue(row.getCell(16)));
		employee.setLevelAndGrade(getStringCellValue(row.getCell(20)));
		employee.setLocation(getStringCellValue(row.getCell(21)));
		employee.setMartialStatus(getStringCellValue(row.getCell(22)));
		employee.setMobileNumber(getStringCellValue(row.getCell(24)));
		employee.setOSIEmailId(getStringCellValue(row.getCell(26)));
		employee.setReportingManager(getStringCellValue(row.getCell(33)));
		employee.setRMEmailId(getStringCellValue(row.getCell(34)));
		employee.setStrategic(getStringCellValue(row.getCell(36)));
		employee.setSubPractice(getStringCellValue(row.getCell(37)));
		employee.setTypeOfEmployment(getStringCellValue(row.getCell(41)));
		employee.setUserName(getStringCellValue(row.getCell(42)));

		// You might need to handle employee projects here and populate the
		// 'employeeProject' list

		return employee;
	}

	private String getStringCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue();
	}

	public EmployeeResponseDto getEmployeeProjectData(String employeeName) {
		System.out.println(employeeName);
		Employee employee = employeeRepository.findByEmployeeNameLike(employeeName);
		System.out.println(employee + " Nulllll Data");
		List<Object[]> employeeProject = employeeProjectRepo.findProjectDetailsByEmployeeNameContaining(employeeName);
		System.out.println(employeeProject.toString());
		EmployeeResponseDto responseDto = new EmployeeResponseDto();
		// responseDto.seEm(employee.getEmployeeFullName());
		responseDto.setEmployeeId(employee.getEmployeeId());
		responseDto.setEmployeeName(employee.getEmployeeName());
		responseDto.setDesignation(employee.getDesignation());
		responseDto.setGlobalJobcode(employee.getGlobalJobCode());
		responseDto.setJoiningExperience(employee.getJoiningExperience());
		responseDto.setLevelandgrade(employee.getLevelAndGrade());
		responseDto.setReportingManager(employee.getReportingManager());
		responseDto.setTotalExperience(employee.getJoiningExperience());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		List<String> projectList = new ArrayList();
		List<LocalDate> start = new ArrayList<>();
		List<LocalDate> end = new ArrayList<>();

		List<EmployeeRepoDto> projectDetailsList = new ArrayList<>();

		for (Object[] emp : employeeProject) {

			EmployeeRepoDto res = new EmployeeRepoDto();
			res.setProject(Objects.toString(emp[0], null));
			res.setWeekStartDate((Timestamp) emp[1]);
			res.setWeekEndDate((Timestamp) emp[2]);
			projectDetailsList.add(res);
			System.out.println(res.toString() + res.getProject() + res.getWeekEndDate() + "ghvxdwahcvuevarucvueaf");
		}

		for (EmployeeRepoDto repo : projectDetailsList) {
			projectList.add(repo.getProject());

			java.util.Date utilDate = new java.util.Date(repo.getWeekStartDate().getTime());

			// Convert to java.time.LocalDate
			LocalDate localDate = utilDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

			java.util.Date utilDate1 = new java.util.Date(repo.getWeekEndDate().getTime());

			// Convert to java.time.LocalDate
			LocalDate localDate1 = utilDate1.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

			start.add(localDate);

			end.add(localDate1);

		}

		responseDto.setProjectName(projectList);
		responseDto.setWeekStartDate(start);
		responseDto.setWeekEndDate(end);
		return responseDto;
	}
	public List<Employee> getEmployeeNames(String name){
		return employeeRepository.findAllByEmployeeNameLike("%"+name+"%");
	}
}
