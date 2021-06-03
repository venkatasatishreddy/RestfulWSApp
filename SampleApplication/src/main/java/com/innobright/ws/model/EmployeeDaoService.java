package com.innobright.ws.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDaoService {
	
	public static List<Employee> employees = new ArrayList<>();
	
	static {
		employees.add(new Employee("1", "Johnny", "Depp", "jd@gmail.com", new Date()));
		employees.add(new Employee("2" ,"Keira", "knightley", "kk@gmail.com", new Date()));
		employees.add(new Employee("3", "Orlando", "Bloom", "ob@gmail.com", new Date()));
	}
	
	public List<Employee> findAll(){
		return employees;
	}
	
	public Employee findOne(String id) {
		for (Employee employee1 : employees) {
			if(employee1.getId().equals(id))
				return employee1;
		}
		return null;		
	}
	
	public Employee	save(Employee employee) {
		if(employee.getId() == null)
			employee.setId(UUID.randomUUID().toString());
		employees.add(employee);
		return employee;		
	}

}
