package com.Employee.Directory.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Directory.Model.Employee;
import com.Employee.Directory.Service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;

	@GetMapping("/people")
	public ResponseEntity<List<Employee>> getEmployees(){
		return service.getEmployees();
	}
	
	@GetMapping("/people/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id){
		return service.getEmployee(id);
	}
	
	@PostMapping("/people")
	public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee emp){
		return service.addEmployee(emp);
	}
	
	@PutMapping("/people/{id}")
	public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employee emp , @PathVariable int id){
		return service.updateEmployee(id,emp);
	}
	
	@DeleteMapping("/people/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id){
		return service.deleteEmployee(id);
	}

}
