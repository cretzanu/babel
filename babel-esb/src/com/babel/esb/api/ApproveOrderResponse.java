package com.babel.esb.api;

import com.babel.accounting.Invoice;
import com.babel.order.Order;
import com.babel.order.process.OrderProcessData;
import com.babel.production.ProductionPlan;

public class ApproveOrderResponse {
	Order order;
	Invoice invoice;
	ProductionPlan productionPlan;
	OrderProcessData processData;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public ProductionPlan getProductionPlan() {
		return productionPlan;
	}
	public void setProductionPlan(ProductionPlan productionPlan) {
		this.productionPlan = productionPlan;
	}
	public OrderProcessData getProcessData() {
		return processData;
	}
	public void setProcessData(OrderProcessData processData) {
		this.processData = processData;
	}
	

}
