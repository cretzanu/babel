package com.babel.order;

import com.babel.core.function.crud.Create;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:53 AM
 */
public interface CreateOrder extends Create {

	/**
	 * 
	 * @param p
	 */
	public Order createOrder(Order p);

}