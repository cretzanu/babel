package com.babel.core.data.search.engine;

import java.util.List;

import javax.persistence.EntityManager;

import com.babel.core.data.search.criteria.SearchCriteria;


public interface SearchEngine {

	List<?> resultList(SearchCriteria request, EntityManager em);
	Object singleResult (SearchCriteria request, EntityManager em);
	
	<T> List<T> resultList(SearchCriteria request, Class<T> returnClassType, EntityManager em);
	<T> T singleResult (SearchCriteria request, Class<T> returnClassType, EntityManager em);
}
