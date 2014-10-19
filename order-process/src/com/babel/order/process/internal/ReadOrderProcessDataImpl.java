package com.babel.order.process.internal;

import java.util.List;

import com.babel.core.function.crud.ReadByIdImpl;
import com.babel.order.process.OrderProcessData;

public class ReadOrderProcessDataImpl extends ReadByIdImpl implements ReadOrderProcessData {
//	private EntityManager em;
//	private Repository repository = new RepositoryJPA();
//	
//	public void setEm(EntityManager em){
//		((RepositoryJPA) this.repository).setEm(em);
//		this.em=em;
//	}
	@Override
	public OrderProcessData readById(Long id) {
		return super.readById(OrderProcessData.class, id);
	}

	@Override
	public List<OrderProcessData> readByOrderId(Long orderId) {
		
		return null;
	}

	@Override
	public List<OrderProcessData> readByInvoiceId(Long invoiceId) {
		
		return null;
	}

}
