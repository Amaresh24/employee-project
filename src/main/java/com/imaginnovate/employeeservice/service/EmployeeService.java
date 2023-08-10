package com.imaginnovate.employeeservice.service;

import java.util.List;

import com.imaginnovate.employeeservice.entity.Employee;
import com.imaginnovate.employeeservice.response.EmployeeResponse;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public String deleteEmployee(long empId);

	public EmployeeResponse getById(Long empId);

}
