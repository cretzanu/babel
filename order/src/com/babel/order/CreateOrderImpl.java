package com.babel.order;

import com.enterprise.core.function.crud.CreateImpl;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:53 AM
 */
public class CreateOrderImpl extends CreateImpl implements CreateOrder {

	public CreateOrderImpl() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param p
	 */
	public Order createOrder(Order p) {
		return super.create(p);
	}
}// end CreateOrderImpl