package com.babel.production;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

import com.babel.core.data.PersistentEntity;
@Entity
public class ProductionPlanRow extends PersistentEntity{
	@ManyToOne
	@JsonBackReference
	private ProductionPlan plan;
	private String productDescription;
	double quantity;
	
	public ProductionPlanRow(String productDescription, double quantity) {
		super();
		this.productDescription = productDescription;
		this.quantity = quantity;
	}
	public ProductionPlanRow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductionPlan getPlan() {
		return plan;
	}
	public void setPlan(ProductionPlan plan) {
		this.plan = plan;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	
}
