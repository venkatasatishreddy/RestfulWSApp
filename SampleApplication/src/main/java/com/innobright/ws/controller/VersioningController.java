package com.innobright.ws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innobright.ws.model.Employee1;
import com.innobright.ws.model.Employee2;
import com.innobright.ws.model.Name;

/*
 * By usig this controller will get to know how to manage versionings in Application.
 * We have a multiple aproaches on this. We are going to see all types in this controller.
 */

@RestController
public class VersioningController {
	
	/*
	 * URI Versioning.
	 */
	@GetMapping("v1/employee")
	public Employee1 getEmployee1() {
		return new Employee1("Johny Deep");
	}
	
	@GetMapping("v2/employee")
	public Employee2 getEmployee2() {
		return new Employee2(new Name("Johny", "Deep"));
	}
	
	/*
	 * Requestparam versioning.
	 * employee/param?version=1
	 */
	@GetMapping(value = "employee/param", params = "version=1")
	public Employee1 paramv1() {
		return new Employee1("Johny Deep");
	}
	
	@GetMapping(value = "employee/param", params = "version=2")
	public Employee2 paramv2() {
		return new Employee2(new Name("Johny", "Deep"));
	}
	
	/*
	 * Header versioning.
	 */
	@GetMapping(value = "employee/header", headers = "API-VERSION=1")
	public Employee1 headerv1() {
		return new Employee1("Johny Deep");
	}
	
	@GetMapping(value = "employee/header", headers = "API-VERSION=2")
	public Employee2 headerv2() {
		return new Employee2(new Name("Johny", "Deep"));
	}
	
	/*
	 * Meadiatype versioning.
	 */
	@GetMapping(value = "employee/produces", produces = "application/innobright.app-v1+json")
	public Employee1 producesv1() {
		return new Employee1("Johny Deep");
	}
	
	@GetMapping(value = "employee/produces", produces = "application/innobright.app-v2+json")
	public Employee2 producesv2() {
		return new Employee2(new Name("Johny", "Deep"));
	}

}
