package com.babel.order;

import java.util.List;

/**
 * 
 * @author liviu.cretu
 * 
 */
public interface LoadOrdersBatch {

	/**
	 * Creates Order records for all orders received. Silently ignores any error
	 * that may appear on individual orders and continues with the rest of the
	 * collection.
	 * 
	 * @param orders
	 * @return the number of orders successfully processed
	 * @throws OrderBatchException
	 *             - the message contains the list of customers having orders in
	 *             error
	 */
	
	public void loadOrdersBatch(List<Order> orders)
			throws OrdersBatchException;

}
