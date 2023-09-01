package com.osi.fileuploader.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRepoDto {
    String project;
    Timestamp weekEndDate;
    Timestamp  weekStartDate;
    
    
    
}
