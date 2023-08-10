package com.imaginnovate.employeeservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.employeeservice.entity.Employee;
import com.imaginnovate.employeeservice.response.EmployeeResponse;
import com.imaginnovate.employeeservice.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("employee")
@Slf4j
public class EmployeeController {

	private @Autowired EmployeeService employeeService;

//requirement 1  -> saving employee with multiple phone num
	@PostMapping("saveemployee")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
		log.info("saving employee {}",employee);
		return ResponseEntity.ok(employeeService.saveEmployee(employee));
	}

	// requirement 2    ->getting employee by id with tax as per req.
	@GetMapping("getemployeebyid/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
		log.info("getting  employees ");
		return ResponseEntity.ok(employeeService.getById(id));
	}

	//update employee
	@PutMapping("updateemployee")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) {
		log.info("updating  employees ");
		return ResponseEntity.ok(employeeService.updateEmployee(employee));
	}

	//getting all employee list
	@GetMapping("getallemployees")
	public ResponseEntity<List<Employee>> getallEmployees() {
		log.info("getting list of  employees ");
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

}
