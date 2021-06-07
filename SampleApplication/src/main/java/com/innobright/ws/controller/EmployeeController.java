package com.innobright.ws.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innobright.ws.exception.EmployeeNotFoundException;
import com.innobright.ws.model.Employee;
import com.innobright.ws.model.EmployeeDaoService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Resource
	EmployeeDaoService service;
	
	/*
	 * By using the produces param we can provide multiple responses to clients.
	 * Below methos will suport both Json and Xml formats. As per client requirement
	 * it will respond.
	 * 
	 * @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE,
	 * MediaType.APPLICATION_JSON_VALUE })
	 */
	/*
	 * @GetMapping public ResponseEntity<Employee> getEmpoyees() { Employee employee
	 * = new Employee("1", "Satish", "Reddy", "s@gamil.com"); 
	 * // By using ResponseEntity we can pass our own HttpStatus in a response.
	 * ResponseEntity<Employee> re = new ResponseEntity<Employee>(employee,
	 * HttpStatus.OK); return re; }
	 */
	
	@GetMapping
	public List<Employee> getEmpoyees() {		
		return service.findAll();
	}
	
	/*
	 * @GetMapping(path = "/{empId}") public Employee
	 * getEmpoyeeById(@PathVariable(name = "empId") String id) { // return
	 * String.format("GetEmp , %s", id); Employee findOne = service.findOne(id); //
	 * if(findOne.getId() == null) // return null; if(findOne == null) throw new
	 * EmployeeNotFoundException("Employee details not found on this id : " + id);
	 * else return service.findOne(id); }
	 */
	
	/*
	 * In below method we are implementing HATEOAS functionality, by using this we
	 * can provide additional links in response. Ex: By using Id we are getting
	 * emaployee details and along with that i have to show one link to get all
	 * employee deatails.
	 */
	@GetMapping(path = "/{empId}")
	public EntityModel<Employee> getEmpoyeeById(@PathVariable(name = "empId") String id) {
		Employee employee = service.findOne(id);
		if(employee == null)
			throw new EmployeeNotFoundException("Employee details not found on this id : " + id);
		
		EntityModel<Employee> model = EntityModel.of(employee);
		
//		WebMvcLinkBuilder linkToAllEmployees = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEmpoyees());	
//		we can minimize abt stmt with static imports.
//		We are linking url on getEmployee method to get all employee details.
		WebMvcLinkBuilder linkToAllEmployees = linkTo(methodOn(this.getClass()).getEmpoyees());	
		
//		we have to provide relation to identify the link in response.
		model.add(linkToAllEmployees.withRel("all-employees"));		
		
		return model;
	}
	
//	Ex: http://localhost:8080/employees?page=2&limit25&sort=asc
//	if we didn't pass values for page, limit and sort, it will take default values.
//	@GetMapping
//	public String getEmpoyeeById(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
//			@RequestParam(value = "limit", defaultValue = "50") int limit,
//			@RequestParam(value = "sort", defaultValue = "asc") String sort) {
//		
//		return "page = "+ page + " and limt = " + limit + " and sort = " + sort;
//	}
	
	@PostMapping
	public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee) {
		Employee saveEmployee = service.save(employee);
		
		/*
		 * ServletUriComponentsBuilder.fromCurrentRequest() --> will get current Http request path
		 * it will takes upto http://localhost:8080/employees
		 * path("/{id}").buildAndExpand(saveEmployee.getId()) -->
		 * buildAndExpand method is used to pass data to path method
		 * EX : Finally will get this as an uri in reponse header like below
		 * http://localhost:8080/employees/{id} --> http://localhost:8080/employees/4
		 */
		
		System.out.println("Req uri : " + ServletUriComponentsBuilder.fromCurrentRequest().toUriString());		
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(saveEmployee.getId()).toUri();
		
//		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(saveEmployee, HttpStatus.CREATED);
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public String updateEmployee() {
		return "";
	}
	
	@DeleteMapping
	public String deleteEmployee() {
		return "";
	}
	
}

