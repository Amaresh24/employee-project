package com.imaginnovate.employeeservice.serviceimpl;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.employeeservice.entity.Employee;
import com.imaginnovate.employeeservice.exception.ResourceNotFoundException;
import com.imaginnovate.employeeservice.repository.EmployeeRepository;
import com.imaginnovate.employeeservice.response.EmployeeResponse;
import com.imaginnovate.employeeservice.service.EmployeeService;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public String deleteEmployee(long empId) {
		if (!employeeRepository.existsById(empId)) {
			throw new ResourceNotFoundException("employee does not exits");
		}
		employeeRepository.deleteById(empId);
		return "deleted";
	}

	@Override
	public EmployeeResponse getById(Long empId) {
		if (!employeeRepository.existsById(empId)) {
			throw new ResourceNotFoundException("employee does not exits");
		}
		Employee employee = employeeRepository.findById(empId).get();
		return EmployeeResponse.builder().cessAmount(cessCalculate(employee.getSalaryPerMonth())).employeeCode(empId)
				.firstName(employee.getFirstName()).lastName(employee.getLastName())
				.taxAmount(taxCalculate(salCalculate(employee))).yearlySalary(employee.getSalaryPerMonth() * 12)
				.build();
	}

	private double cessCalculate(double sal) {
		if (sal > 250000)
			return 0;
		else
			return (sal - 250000) * 0.02;
	}

	private double salCalculate(Employee employee) {

		Double salaryperMonth = employee.getSalaryPerMonth();
		Date joiningDate = employee.getDateOfJoining();

		LocalDate date1 = joiningDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate date2 = LocalDate.of(2023, Month.MARCH, 30);

		Period period = Period.between(date1, date2);
		int monthsDifference = period.getMonths();
		int daysDifference2 = period.getDays();

		double salarymonthly = salaryperMonth * monthsDifference;
		double salarydaywise = (salaryperMonth / 30) * daysDifference2;

		return salarymonthly + salarydaywise;
	}

	private double taxCalculate(double yearlySalary) {
		if (yearlySalary <= 250000) {
			return 0;
		} else if (yearlySalary <= 500000) {
			return 0.05 * (yearlySalary - 250000);
		} else if (yearlySalary <= 1000000) {
			return 0.1 * (yearlySalary - 500000) + 12500;
		} else {
			return 0.2 * (yearlySalary - 1000000) + 112500;
		}
	}

}
