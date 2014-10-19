package com.babel.production;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.babel.core.data.PersistentEntity;
@Entity
public class ProductionPlan extends PersistentEntity{
	@XmlElement
	@XmlElementWrapper(name="planRows")
	@OneToMany(mappedBy="plan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)  @org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN) 
	@JsonManagedReference 	
	private Set<ProductionPlanRow> planRows=new HashSet<ProductionPlanRow>();

	public ProductionPlan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<ProductionPlanRow> getPlanRows() {
		return planRows;
	}

	public void setPlanRows(Set<ProductionPlanRow> planRows) {
		this.planRows = planRows;
	}

	public void addPlanRow(ProductionPlanRow p){
		this.planRows.add(p);
		p.setPlan(this);
	}
	
	
}
