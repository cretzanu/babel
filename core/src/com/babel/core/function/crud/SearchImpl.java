package com.babel.core.function.crud;

import java.util.List;

import javax.persistence.EntityManager;

import com.babel.core.data.repo.Repository;
import com.babel.core.data.repo.RepositoryJPA;
import com.babel.core.data.search.criteria.SearchCriteria;

public class SearchImpl implements Search {
	Repository repository = new RepositoryJPA();

	public void setEm(EntityManager em){
		((RepositoryJPA)this.repository).setEm(em);
	}
	
	public  List search(SearchCriteria request){
		return repository.search(request);
	}
}
