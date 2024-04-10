package com.example.demo.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	//build Get all employee rest api
	@GetMapping
	public List <Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	//build get Employee by id RestApi
	//http://localhost:8080/api/employees/1
	@GetMapping("{id}")  //url templet variable
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long  employeeId) {
		
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
		
	}
	
	//built update employee restapi
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id
			,@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
		
		
	}
	
	//build delete employee rest api
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		employeeService.deletEmployee(id);
		return new ResponseEntity<String>("Employee deleted successfully !", HttpStatus.OK);
		
	}
	
}
