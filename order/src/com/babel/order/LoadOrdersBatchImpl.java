package com.babel.order;

import java.util.LinkedList;
import java.util.List;

public class LoadOrdersBatchImpl implements LoadOrdersBatch {

	private CreateOrder createOrder;

	public LoadOrdersBatchImpl() {
	}

	public LoadOrdersBatchImpl(CreateOrder c) {
		this.createOrder = c;
	}

	@Override
	public void loadOrdersBatch(List<Order> orders) throws OrdersBatchException {
		OrdersBatchException errors = null;
		for (Order order : orders) {
			System.out.println("processing order - " + order);
			try {
				createOrder.createOrder(order);
			} catch (Throwable t) {
				if (errors == null)
					errors = new OrdersBatchException();
				errors.addOrderInError(order);
				t.printStackTrace();
			}
		}
		if (errors != null)
			throw errors;

	}

	public CreateOrder getCreateOrder() {
		return createOrder;
	}

	public void setCreateOrder(CreateOrder createOrder) {
		this.createOrder = createOrder;
	}

}
