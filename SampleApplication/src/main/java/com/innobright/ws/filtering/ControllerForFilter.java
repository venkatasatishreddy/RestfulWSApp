package com.innobright.ws.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerForFilter {
	
	@GetMapping(path = "/filtering")
	public FilterClass getFilter() {
		return new FilterClass("v1", "v2", "v3");
	}
	
	@GetMapping(path = "/list-filtering")
	public List<FilterClass> getFilters() {
		return Arrays.asList(new FilterClass("v1", "v2", "v3"), new FilterClass("v1", "v2", "v3"));
	}

}
