package com.Employee.Directory.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Employee.Directory.Model.Employee;
import com.Employee.Directory.Repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repo;

	public ResponseEntity<List<Employee>> getEmployees(){
		try {
			List<Employee> lst = repo.findAll();
			return new ResponseEntity<List<Employee>>(lst,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Employee>>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Employee> getEmployee(int id) {
		Employee emp = repo.findById(id).orElse(null);
		if(emp == null)return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}

	public ResponseEntity<?> addEmployee(Employee emp) {
		
		
		emp.setEmail(emp.getEmail().toLowerCase().trim());
		emp.setDepartment(emp.getDepartment().trim());
		emp.setName(emp.getName().trim());
		
		if(repo.existsByEmailIgnoreCase(emp.getEmail())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		repo.save(emp);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}

	public ResponseEntity<?> updateEmployee(int id, Employee emp) {
		Employee employee = repo.findById(id).orElse(null);
		if(employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		String email = emp.getEmail().toLowerCase().trim();
		
		if(!email.equals(employee.getEmail()) &&
				repo.existsByEmailIgnoreCase(email))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		employee.setName(emp.getName().trim());
		employee.setDepartment(emp.getDepartment().trim());
		employee.setEmail(emp.getEmail().toLowerCase().trim());
		employee.setJoinedOn(emp.getJoinedOn());
		employee.setRole(emp.getRole());
		
		
		repo.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<?> deleteEmployee(int id) {
		Employee employee = repo.findById(id).orElse(null);
		if(employee == null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		repo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	

}
