package com.babel.esb.transformer;

import com.babel.accounting.Invoice;
import com.babel.order.Order;
import com.babel.order.OrderLine;
import com.babel.production.ProductionPlan;
import com.babel.production.ProductionPlanRow;

public class OrderToProductionPlanTransformer {

	public ProductionPlan transform(Order order){
		ProductionPlan plan = new ProductionPlan();
		for (OrderLine l:order.getOrderLines())
			plan.addPlanRow(new ProductionPlanRow(l.getItem(),l.getQuantity()));
		return plan;
		
	}
}
