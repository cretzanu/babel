package com.babel.order.batch;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.babel.order.LoadOrdersBatch;
import com.babel.order.LoadOrdersBatchImpl;
import com.babel.order.Order;
import com.babel.order.OrdersBatchException;

@javax.ejb.Stateless
@javax.ejb.Remote(value = LoadOrdersBatch.class)
@javax.ejb.Local(value = LoadOrdersBatch.class)
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
public class LoadOrdersBatchEJB implements LoadOrdersBatch {
	LoadOrdersBatch loadOrdersDelegate;
	@EJB
	CreateOrderBatch createOrderDelegate;
	
	@javax.annotation.PostConstruct
	public void init() {
		this.loadOrdersDelegate = new LoadOrdersBatchImpl(createOrderDelegate);
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void loadOrdersBatch(List<Order> orders) throws OrdersBatchException {
		this.loadOrdersDelegate.loadOrdersBatch(orders);
		
	}
}
