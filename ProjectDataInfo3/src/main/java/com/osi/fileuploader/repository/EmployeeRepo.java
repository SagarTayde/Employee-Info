package com.osi.fileuploader.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.osi.fileuploader.pojos.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	@Query(nativeQuery = true, value = "SELECT e1_0.id, e1_0.bu, e1_0.osiemail_id, e1_0.rmemail_id, e1_0.blood_group, e1_0.contract_type, e1_0.date_of_birth, e1_0.date_of_exit, e1_0.date_of_joining, e1_0.department, e1_0.designation, e1_0.employee_full_name, e1_0.employee_id, e1_0.employee_name, e1_0.employee_status, e1_0.entity, e1_0.gender, e1_0.global_job_code, e1_0.joining_experience, e1_0.level_and_grade, e1_0.location, e1_0.martial_status, e1_0.mobile_number, e1_0.reporting_manager, e1_0.strategic, e1_0.sub_practice, e1_0.type_of_employment, e1_0.user_name FROM employee e1_0 WHERE e1_0.employee_name LIKE CONCAT('%', :employeeName, '%');")
	Employee findByEmployeeNameLike(String employeeName);
	
	List<Employee> findAllByEmployeeNameLike(String employeeName);
	
	
}
