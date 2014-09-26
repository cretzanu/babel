package com.babel.order;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:53 AM
 */

public class CreateOrderLocalJPA extends CreateOrderImpl {

	
	

	public CreateOrderLocalJPA() {

	}

	
	/**
	 * 
	 * @param p
	 */
	public Order createOrder(Order p) {
		this.getEm().getTransaction().begin();
		Order r=super.createOrder(p);
		this.getEm().getTransaction().commit();
		return r;
	}

	
}// end CreateOrderEJB