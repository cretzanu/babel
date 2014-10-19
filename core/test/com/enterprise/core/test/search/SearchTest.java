package com.enterprise.core.test.search;

import java.util.Date;

import com.babel.core.data.search.criteria.Operator;
import com.babel.core.data.search.engine.SearchEngineFactory;
import com.babel.core.data.search.engine.SearchEntity;


public class SearchTest {
	
	private static final String CHECK_1 = "select t from DigitEntity t where 1=1  AND t.dateUpdated>:dateUpdated AND t.updatedByUser=:updatedByUser";

	public static void main(String[] args){
		new SearchTest().test1();
		//new SearchTest().testIgnoreNullValuePredicates();
		//new SearchTest().testOracleDates();
	}
	
	
	public void test1(){
		SearchEntityTest sb=new SearchEntityTest();
		sb.getUser().setOperator(Operator.EQUALS_TO);
		sb.getUser().setValue("abc");
		sb.getDateUpdated().setOperator(Operator.GREATER_THEN);
		sb.getDateUpdated().setValue(new Date());
		SearchEntity sr=(SearchEntity)new SearchEngineFactory().engineFactory(sb);
		String buildQuery = sr.buildQueryString(sb);
		System.out.println(buildQuery);
		//Assert....
		boolean equals = buildQuery.equals(CHECK_1);
		if (!equals)
			throw new RuntimeException("Test1 failed: generated query is not the right one");
		
	}

	
	
}
