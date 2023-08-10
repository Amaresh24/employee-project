package com.imaginnovate.employeeservice.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Entity
@Builder
@Data
public class Employee {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Id Long employeeId;

	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	private String email;
	@OneToMany(mappedBy = "phnoid", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<PhoneNumber> phoneNumber;
	@NonNull
	private Date dateOfJoining;
	@NonNull
	private Double salaryPerMonth;

}
