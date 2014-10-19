package com.babel.order.process;

import com.babel.order.Order;

public interface ApproveOrder {

	public void approveOrder(Order p);
	public void approveOrder(Long id);
}
