package com.babel.core.data.search.criteria;

public enum LogicalOperator {
	
	AND,
	OR;
	public String toString(){
		switch(this) {
		
		case AND : return " AND ";
		case OR : return " OR ";
		default : return "N/A"; 
		}
	}
	
}
