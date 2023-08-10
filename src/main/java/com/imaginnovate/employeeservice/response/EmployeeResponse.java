package com.imaginnovate.employeeservice.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeResponse {

	private Long employeeCode;
	private String firstName;
	private String lastName;
	private Double yearlySalary;
	private double taxAmount;
	private double cessAmount;

}
