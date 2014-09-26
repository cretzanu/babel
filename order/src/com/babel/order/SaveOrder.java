package com.babel.order;

import com.enterprise.core.function.crud.Update;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:54 AM
 */
public interface SaveOrder extends Update {

	/**
	 * 
	 * @param p
	 */
	public Order saveOrder(Order p);

}