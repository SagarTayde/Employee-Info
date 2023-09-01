package com.osi.fileuploader.service;

import java.sql.Timestamp;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.osi.fileuploader.pojos.EmployeeProject;
import com.osi.fileuploader.repository.EmployeeProjectRepo;

@Service
public class EmployeeProjectExcelUploadService {

	@Autowired
	private EmployeeProjectRepo employeeProjectRepository;

	public void processExcelFile(MultipartFile file) throws Exception {

		employeeProjectRepository.deleteAll();
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next(); // Skip header row

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			EmployeeProject employeeProject = new EmployeeProject();

			employeeProject.setActivity(getStringCellValue(row.getCell(0)));
			employeeProject.setApproverHist(getStringCellValue(row.getCell(1)));
			employeeProject.setDeliveryManager(getStringCellValue(row.getCell(2)));
			employeeProject.setEmployee(getStringCellValue(row.getCell(3)));
			employeeProject.setEmpBU(getStringCellValue(row.getCell(4)));
			employeeProject.setEmpOrganisation(getStringCellValue(row.getCell(5)));
			employeeProject.setEmpPractice(getStringCellValue(row.getCell(6)));
			employeeProject.setEmpregion(getStringCellValue(row.getCell(7)));
			employeeProject.setEmpStrategicAccount(getStringCellValue(row.getCell(8)));
			employeeProject.setEmpSubPractice(getStringCellValue(row.getCell(9)));
			employeeProject.setEmpXferEntity(getStringCellValue(row.getCell(10)));
			// employeeProject.setHours(getIntCellValue(row.getCell(11)));
			employeeProject.setMonth(getStringCellValue(row.getCell(12)));
			// employeeProject.setNotes(getStringCellValue(row.getCell(13)));
			employeeProject.setProject(getStringCellValue(row.getCell(14)));
			System.out.println(row.getCell(21).getLocalDateTimeCellValue());
			Timestamp timestamp = Timestamp.valueOf(row.getCell(21).getLocalDateTimeCellValue());

			employeeProject.setWeekEndDate(timestamp);
			Timestamp timestamp1 = Timestamp.valueOf(row.getCell(21).getLocalDateTimeCellValue());

			employeeProject.setWeekStartDate(timestamp1);

			employeeProjectRepository.save(employeeProject);
		}

		workbook.close();
	}

	private String getStringCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue();

	}

}
