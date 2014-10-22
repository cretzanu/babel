package com.babel.order.batch;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.babel.order.CreateOrder;
import com.babel.order.Order;

@Stateless
@Local (CreateOrderBatch.class)//for the moment, only local calls are needed
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
public class CreateOrderBatchEJB implements CreateOrderBatch{
	@EJB //inject the original service 
	private CreateOrder delegate;
	
//	@javax.persistence.PersistenceContext(unitName="myJPAUnit")
//	private javax.persistence.EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Order createOrder(Order p){
		return this.delegate.createOrder(p);
	}
//
//	@javax.annotation.PostConstruct
//	public void init(){
//		this.delegate=new CreateOrderImpl(); 
//		 (( CreateOrderImpl)this.delegate).setEm(em);
//	}
}