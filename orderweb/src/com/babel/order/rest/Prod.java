package com.babel.order.rest;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonManagedReference;

public class Prod {

	@JsonManagedReference
	private List<Linie> lines=new LinkedList<Linie>();

	public List<Linie> getLines() {
		return lines;
	}

	public void setLines(List<Linie> lines) {
		this.lines = lines;
	}
	
	
}
