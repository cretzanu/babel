package com.babel.order.process;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;

import com.babel.order.Order;
import com.babel.order.process.impl.ApproveOrderImpl;

@Stateless
@Remote(ApproveOrder.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApproveOrderEJB implements ApproveOrder{
	ApproveOrder delegate;
	@javax.persistence.PersistenceContext(unitName="order-process-ejb")
	private javax.persistence.EntityManager em;

	@PostConstruct
	public void init(){
		delegate=new ApproveOrderImpl();
		//init dependencies
		OrderProcessFactory.getInstance().setEm(em);
		((ApproveOrderImpl)delegate).setCreateInvoice(OrderProcessFactory.getInstance().createInvoiceFactory());
		((ApproveOrderImpl)delegate).setCreateOrderProcessDataDelegate(OrderProcessFactory.getInstance().createOrderProcessDataFactory());
		((ApproveOrderImpl)delegate).setCreateProductionPlanDelegate(OrderProcessFactory.getInstance().createProductionPlanFactory());
		((ApproveOrderImpl)delegate).setReadOrderDelegate(OrderProcessFactory.getInstance().readOrderFactory());
		((ApproveOrderImpl)delegate).setReadOrderProcessDataDelegate(OrderProcessFactory.getInstance().readOrderProcessDataFactory());
		((ApproveOrderImpl)delegate).setSaveOrderDelegate(OrderProcessFactory.getInstance().saveOrderFactory());
		((ApproveOrderImpl)delegate).setSendEmailDelegate(OrderProcessFactory.getInstance().sendEmailFactory());
	}

	@Override
	public void approveOrder(Order p) {
		this.delegate.approveOrder(p);
		
	}

	@Override
	public void approveOrder(Long id) {
		this.delegate.approveOrder(id);
		
	}
	
}
