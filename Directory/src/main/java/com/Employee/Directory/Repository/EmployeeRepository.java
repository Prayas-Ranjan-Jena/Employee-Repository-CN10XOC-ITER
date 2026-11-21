package com.Employee.Directory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.Directory.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	boolean existsByEmailIgnoreCase(String email);
	
}
