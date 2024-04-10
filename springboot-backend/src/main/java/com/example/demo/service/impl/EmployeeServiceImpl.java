package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ecxeption.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//		return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
		return employeeRepository.findById(id).orElseThrow(()-> 
			new ResourceNotFoundException("Employee", "Id", id));
		
	}

	public Employee updateEmployee(Employee employee, long id) {
		//check employee id is existing in the database
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// Save existing employee to db
		employeeRepository.save(existingEmployee);

		return existingEmployee;
	}

	@Override
	public void deletEmployee(long id) {
		
		//check whether emp exist in db or not
		
		employeeRepository.findById(id).orElseThrow(()->
									new ResourceNotFoundException("Employee", "Id", id));
		
		employeeRepository.deleteById(id);
	}

}
