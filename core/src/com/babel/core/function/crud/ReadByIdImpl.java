package com.babel.core.function.crud;

import javax.persistence.EntityManager;

import com.babel.core.data.repo.RepositoryJPA;

public  abstract class ReadByIdImpl extends RepositoryJPA implements ReadById {

	
	public <T> T readById(Class<T> meta, Long id){
		return this.readObjectById(meta, id);
	}
	
	

	
}
