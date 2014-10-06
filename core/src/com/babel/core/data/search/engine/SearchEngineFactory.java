package com.babel.core.data.search.engine;

import com.babel.core.data.search.criteria.SearchCriteria;
import com.babel.core.data.search.criteria.SearchEntityCriteria;
import com.babel.core.data.search.criteria.SearchNamedQueryCriteria;

public class SearchEngineFactory {

	
	public SearchEngine engineFactory(SearchCriteria request){
		if (request instanceof SearchEntityCriteria)
			return new SearchEntity();
		else if (request instanceof SearchNamedQueryCriteria)
			return new SearchEngineByNamedQuery();
		else
			throw new IllegalArgumentException("engine not available for this tyoe of request:"+request.getClass());  
		
	}
}
