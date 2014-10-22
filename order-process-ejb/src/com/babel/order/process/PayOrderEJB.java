package com.babel.order.process;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.babel.order.Order;
import com.babel.order.process.impl.PayOrderImpl;

@Stateless
@Remote(PayOrder.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PayOrderEJB implements PayOrder{

	private PayOrder delegate;

	@PostConstruct
	public void init() {
		delegate = new PayOrderImpl();
		//use the same service just to simplify this example
		//we still use two different instances of this service...
		((PayOrderImpl) delegate).setDebitDelegate(OrderProcessFactory
				.getInstance().operateBankAccountFactory());
		((PayOrderImpl) delegate).setCreditDelegate(OrderProcessFactory
				.getInstance().operateBankAccountFactory());
	}

	@Override
	public void payOrder(Order order) {
		this.delegate.payOrder(order);
		
	}
}
