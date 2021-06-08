package com.innobright.ws.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class ControllerForFilter {
	
	@GetMapping(path = "/filtering")
	public MappingJacksonValue getFilter() {
		FilterClass filterClass = new FilterClass("v1", "v2", "v3");
//		By using below class we can set what are the propeties have to set in a response.
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("var1", "var2");
		
//		In below stmt we have mentioned on which obj we have to apply MappingJacksonValue.
//		MappingJacksonValue mappingValue = new MappingJacksonValue(filterClass);
//		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilterBean", filter);
//		mappingValue.setFilters(filterProvider);		
		
		return getMappingJacksonValue(filter, filterClass, null);
	}
	
	@GetMapping(path = "/list-filtering")
	public MappingJacksonValue getFilters() {
		List<FilterClass> asList = Arrays.asList(new FilterClass("v1", "v2", "v3"), new FilterClass("v1", "v2", "v3"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("var2", "var3");
		
//		MappingJacksonValue mappingValue = new MappingJacksonValue(asList);
//		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilterBean", filter);
//		mappingValue.setFilters(filterProvider);
		return getMappingJacksonValue(filter, null, asList);
	}
	
	public MappingJacksonValue getMappingJacksonValue(SimpleBeanPropertyFilter filter, FilterClass filterClass, List<FilterClass> asList) {
		MappingJacksonValue mappingValue = filterClass != null ? new MappingJacksonValue(filterClass) : new MappingJacksonValue(asList);
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilterBean", filter);
		mappingValue.setFilters(filterProvider);
		return mappingValue;
	}

}
