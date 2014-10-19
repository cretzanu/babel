package com.babel.order.process.internal;

import java.util.List;

import com.babel.order.process.OrderProcessData;

public interface ReadOrderProcessData {
	
	public OrderProcessData readById(Long id);
	public List<OrderProcessData> readByOrderId(Long orderId);
	public List<OrderProcessData> readByInvoiceId(Long invoiceId);
	

}
