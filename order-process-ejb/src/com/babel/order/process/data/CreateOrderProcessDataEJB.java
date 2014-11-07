package com.babel.order.process.data;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.babel.order.process.OrderProcessData;
import com.babel.order.process.internal.CreateOrderProcessData;
import com.babel.order.process.internal.CreateOrderProcessDataImpl;

@Stateless
@Remote(CreateOrderProcessData.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CreateOrderProcessDataEJB implements CreateOrderProcessData{

	CreateOrderProcessData delegate;
	@javax.persistence.PersistenceContext(unitName="order-process-ejb")
	private javax.persistence.EntityManager em;
	@PostConstruct
	public void init(){
		CreateOrderProcessDataImpl x = new CreateOrderProcessDataImpl();
		x.setEm(em);
		this.delegate=x;
	}
	@Override
	public OrderProcessData createOrderProcessInfo(OrderProcessData p) {
		return this.delegate.createOrderProcessInfo(p);
	}

}
