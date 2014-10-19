package com.babel.order.rest;

import org.codehaus.jackson.annotate.JsonBackReference;

public class Linie {

	private String test;
	@JsonBackReference
	private Prod prod;
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public Prod getProd() {
		return prod;
	}
	public void setProd(Prod prod) {
		this.prod = prod;
	}
	
}
