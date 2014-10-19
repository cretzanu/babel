package com.babel.production;

import com.babel.core.function.crud.CreateImpl;

public class CreateProductionPlanImpl extends CreateImpl implements CreateProductionPlan{

	@Override
	public ProductionPlan createProductionPlan(ProductionPlan plan) {
		// TODO Auto-generated method stub
		return this.create(plan);
	}
	

}
