package com.babel.order.process.internal;

import com.babel.core.function.crud.CreateImpl;
import com.babel.order.process.OrderProcessData;

public class CreateOrderProcessDataImpl extends CreateImpl implements CreateOrderProcessData
{

	@Override
	public OrderProcessData createOrderProcessInfo(OrderProcessData p) {
		return this.create(p);
	}

}
