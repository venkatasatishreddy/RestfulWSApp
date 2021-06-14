package com.innobright.ws.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.innobright.ws.exception.EmployeeNotFoundException;
import com.innobright.ws.model.Employee;
import com.innobright.ws.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Resource
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployee(String id) {
		Optional<Employee> findById = employeeRepository.findById(id);
		if(findById.isPresent())
			return findById.get();
		else 
			throw new EmployeeNotFoundException("Employee not found on this id : " + id);
	}
	
	public ResponseEntity<Employee> createEmployees(Employee employee) {
		employeeRepository.save(employee);
		return new ResponseEntity<Employee>(HttpStatus.CREATED);
	}

	public void updateEmployee(String id, Employee employee) {
		Optional<Employee> findFirst = getAllEmployees().stream().filter(emp -> emp.getId().equals(id)).findFirst();
		if(findFirst.isPresent()) {
			employee.setId(id);
			employeeRepository.save(employee);
		}
		else
			throw new EmployeeNotFoundException("Employee not found on this id : " + id);
		
	}

	public void deleteEmployee(String id) {
		employeeRepository.deleteById(id);
	}


}
