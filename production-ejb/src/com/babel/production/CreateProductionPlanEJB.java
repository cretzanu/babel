package com.babel.production;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@Remote(CreateProductionPlan.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CreateProductionPlanEJB implements CreateProductionPlan{

	CreateProductionPlan delegate;
	@javax.persistence.PersistenceContext(unitName="production-ejb")
	private javax.persistence.EntityManager em;
	
	public CreateProductionPlanEJB(){
		
	}
	@PostConstruct
	public void init(){
		this.delegate=new CreateProductionPlanImpl();
		((CreateProductionPlanImpl)this.delegate).setEm(em);
	}
	@Override
	public ProductionPlan createProductionPlan(ProductionPlan plan) {
		// TODO Auto-generated method stub
		return this.delegate.createProductionPlan(plan);
	}

}
