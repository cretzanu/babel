package com.babel.order;

import com.babel.core.function.crud.UpdateImpl;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:54 AM
 */
public class SaveOrderImpl extends UpdateImpl implements SaveOrder {

	public SaveOrderImpl() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param p
	 */
	public Order saveOrder(Order p) {
		return super.update(p);
	}
}// end SaveOrderImpl