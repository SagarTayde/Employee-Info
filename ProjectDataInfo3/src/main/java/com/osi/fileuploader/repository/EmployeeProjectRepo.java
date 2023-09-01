package com.osi.fileuploader.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fileuploader.pojos.EmployeeProject;

public interface EmployeeProjectRepo extends JpaRepository<EmployeeProject, String> {
//	@Query(nativeQuery = true, value = "SELECT e1_0.id, e1_0.activity, e1_0.approver_hist,"
//			+ " e1_0.delivery_manager, e1_0.empbu, e1_0.emp_organisation, e1_0.emp_practice,"
//			+ " e1_0.emp_strategic_account, e1_0.emp_sub_practice,"
//			+ " e1_0.emp_xfer_entity, e1_0.employee, e1_0.empregion,"
//			+ " e1_0.month, e1_0.project, e1_0.week_end_date,"
//			+ " e1_0.week_start_date FROM employee_project e1_0 WHERE"
//			+ " e1_0.employee LIKE CONCAT('%', :employee, '%');")
//	Select Distinct t1.project,  max(t1.week_end_date),min(t1.week_start_date) from 
//	(SELECT e1_0.id, e1_0.activity, e1_0.approver_hist, e1_0.delivery_manager, e1_0.empbu, e1_0.emp_organisation, e1_0.emp_practice,e1_0.emp_strategic_account, e1_0.emp_sub_practice,e1_0.emp_xfer_entity, e1_0.employee, e1_0.empregion,e1_0.month, e1_0.project, e1_0.week_end_date,e1_0.week_start_date FROM employee_project e1_0 WHERE e1_0.employee LIKE CONCAT('%', employee, '%')) as t1 group by project;
	@Query(nativeQuery = true, value ="Select Distinct t1.project,  max(t1.week_end_date),min(t1.week_start_date) from "
			+ "\r\n"
			+ "	(SELECT e1_0.id, e1_0.activity, e1_0.approver_hist,"
			+ " e1_0.delivery_manager, e1_0.empbu, e1_0.emp_organisation, "
			+ "e1_0.emp_practice,e1_0.emp_strategic_account, e1_0.emp_sub_practice,"
			+ "e1_0.emp_xfer_entity, e1_0.employee, e1_0.empregion,e1_0.month, "
			+ "e1_0.project, e1_0.week_end_date,e1_0.week_start_date"
			+ " FROM employee_project e1_0 WHERE e1_0.employee"
			+ " LIKE CONCAT('%', employee, '%')) as t1 group by project;")
	List<Object[]> findProjectDetailsByEmployeeContaining(String employee);
	
//	@Query(nativeQuery=true,value="select distinct project ,min(week_start_date),max(week_end_date) from employee_test.employee_project \r\n"
//			+ "where employee like '%NAGARJUNA%' group by project;")
//	@Query(nativeQuery = true, value = "SELECT DISTINCT project, MIN(week_start_date), MAX(week_end_date) "
//	        + "FROM employee_test.employee_project "
//	        + "WHERE employee LIKE %:employeeName% "
//	        + "GROUP BY project;")
//	List<EmployeeProject> findEmployeeProjectsByEmployee(String employee);
	
	 @Query(nativeQuery = true, value = "SELECT DISTINCT project, MIN(week_start_date), MAX(week_end_date) "
	            + "FROM employee_test.employee_project "
	            + "WHERE employee LIKE CONCAT('%', :employeeName, '%') "
	            + "GROUP BY project")
	    List<Object[]> findProjectDetailsByEmployeeNameContaining(@Param("employeeName") String employeeName);

	
	
	public void deleteAll();
	
}
