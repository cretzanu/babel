package com.babel.esb.transformer;

import com.babel.accounting.Invoice;
import com.babel.order.Order;
import com.babel.order.OrderLine;
import com.babel.production.ProductionPlan;
import com.babel.production.ProductionPlanRow;

public class OrderToInvoiceTransformer {

	public Invoice transform(Order order){
		Invoice i=new Invoice(); 
		i.setCustomerName(order.getCustomerName());//bank account? 
		i.calculateInvoice(order.calculateOrderValue());
		return i;
		
	}
}
