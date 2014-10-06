package com.babel.order;

import com.babel.core.function.crud.ReadById;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:54 AM
 */
public interface ReadOrder extends ReadById {

	/**
	 * 
	 * @param p
	 */
	public Order readOrder(Long p);

}