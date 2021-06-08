package com.innobright.ws.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Static Filtering:-
 * If we don't want to send some of the properties in response we can utilize JsonIgnore and JsonIgnoreProperties
 * But JsonIgnore is recommended  bcz if any var name change again we have to change in JsonIgnoreProperties
 * 
 * Dynamic Filtering:-
 * It means in one scenario required var1 and var2 and other scenario required var2 and var3.
 * For this we have to utilize MappinJacksonValue.
 * For Dynamic filtering we have to add @JsonFilter(value = "somename") on class, otherwise filter won't work
 */

//@JsonIgnoreProperties({"var2", "var3"})
@JsonFilter(value = "FilterBean")
public class FilterClass {

	private String var1;
//	@JsonIgnore
	private String var2;
//	@JsonIgnore
	private String var3;

	public FilterClass(String var1, String var2, String var3) {
		super();
		this.var1 = var1;
		this.var2 = var2;
		this.var3 = var3;
	}

	public String getVar1() {
		return var1;
	}

	public void setVar1(String var1) {
		this.var1 = var1;
	}

	public String getVar2() {
		return var2;
	}

	public void setVar2(String var2) {
		this.var2 = var2;
	}

	public String getVar3() {
		return var3;
	}

	public void setVar3(String var3) {
		this.var3 = var3;
	}

}
