package com.babel.order;

import com.babel.core.function.crud.ReadByIdImpl;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:54 AM
 */
public class ReadOrderImpl extends ReadByIdImpl implements ReadOrder {

	public ReadOrderImpl() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param p
	 */
	public Order readOrder(Long p) {
		return super.readById(Order.class, p);
	}
}// end ReadOrderImpl