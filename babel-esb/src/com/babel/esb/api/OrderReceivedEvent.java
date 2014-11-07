package com.babel.esb.api;

import com.babel.order.Order;

public class OrderReceivedEvent {

	private Order order;
	
	public OrderReceivedEvent(Order order) {
		super();
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
