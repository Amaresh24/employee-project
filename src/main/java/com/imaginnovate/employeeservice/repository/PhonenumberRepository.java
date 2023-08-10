package com.imaginnovate.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imaginnovate.employeeservice.entity.PhoneNumber;

@Repository
public interface PhonenumberRepository extends JpaRepository<PhoneNumber, Long> {

}
