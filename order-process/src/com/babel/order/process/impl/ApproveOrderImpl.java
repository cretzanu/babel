package com.babel.order.process.impl;

import com.babel.accounting.CreateInvoice;
import com.babel.accounting.Invoice;
import com.babel.ioemail.EmailMessage;
import com.babel.ioemail.SendEmail;
import com.babel.order.Order;
import com.babel.order.OrderLine;
import com.babel.order.ReadOrder;
import com.babel.order.SaveOrder;
import com.babel.order.process.ApproveOrder;
import com.babel.order.process.OrderProcessData;
import com.babel.order.process.ProcessStateType;
import com.babel.order.process.internal.CreateOrderProcessData;
import com.babel.order.process.internal.ReadOrderProcessData;
import com.babel.production.CreateProductionPlan;
import com.babel.production.ProductionPlan;
import com.babel.production.ProductionPlanRow;

public class ApproveOrderImpl implements ApproveOrder{

	private SaveOrder saveOrderDelegate;
	private ReadOrder readOrderDelegate;
	private CreateProductionPlan createProductionPlanDelegate;
	private SendEmail sendEmailDelegate;
	private CreateInvoice createInvoiceDelegate;
	private ReadOrderProcessData readOrderProcessDataDelegate;
	private CreateOrderProcessData createOrderProcessDataDelegate;
	
	@Override
	public void approveOrder(Order order) {		
		//1. create production plan (production system)
		ProductionPlan plan = new ProductionPlan();
		for (OrderLine l:order.getOrderLines())
			plan.addPlanRow(new ProductionPlanRow(l.getItem(),l.getQuantity()));
		plan=this.createProductionPlanDelegate.createProductionPlan(plan);
		//2. create the invoice (accounting)
		Invoice i=new Invoice(); 
		i.setCustomerName(order.getCustomerName());//bank account? 
		i.calculateInvoice(order.calculateOrderValue());
		i=this.createInvoiceDelegate.createInvoice(i);
		//3. save the state of this process instance  
		OrderProcessData opd=new OrderProcessData();
		opd.setOrderId(order.getId());
		opd.setProductionPlanId(plan.getId());
		opd.setProformaInvoiceId(i.getId());
		opd.setProcessState(ProcessStateType.APPROVED);
		opd=this.createOrderProcessDataDelegate.createOrderProcessInfo(opd);
		
		order.setProcessId("ORDER-PROCESS-"+opd.getId());
		this.saveOrderDelegate.saveOrder(order);
		
		//now, inform customer about the progress
		//TODO: send also the invoice as an attachment to this message
		EmailMessage msg=new EmailMessage();
		msg.setTo(order.getCustomerEmail());
		msg.setSubject("Order "+order.getId()+"approved");
		msg.setMessage("....");
		this.sendEmailDelegate.sendEmail(msg);
		
	}
	
	@Override
	public void approveOrder(Long id) {
		this.approveOrder(this.readOrderDelegate.readOrder(id));
		
	}
	

	public CreateProductionPlan getCreateProductionPlanDelegate() {
		return createProductionPlanDelegate;
	}

	public void setCreateProductionPlanDelegate(
			CreateProductionPlan createProductionPlanDelegate) {
		this.createProductionPlanDelegate = createProductionPlanDelegate;
	}

	public SendEmail getSendEmailDelegate() {
		return sendEmailDelegate;
	}

	public void setSendEmailDelegate(SendEmail sendEmailDelegate) {
		this.sendEmailDelegate = sendEmailDelegate;
	}

	public CreateInvoice getCreateInvoice() {
		return createInvoiceDelegate;
	}

	public void setCreateInvoice(CreateInvoice createInvoice) {
		this.createInvoiceDelegate = createInvoice;
	}
	public SaveOrder getSaveOrderDelegate() {
		return saveOrderDelegate;
	}
	public void setSaveOrderDelegate(SaveOrder saveOrderDelegate) {
		this.saveOrderDelegate = saveOrderDelegate;
	}
	public ReadOrder getReadOrderDelegate() {
		return readOrderDelegate;
	}
	public void setReadOrderDelegate(ReadOrder readOrderDelegate) {
		this.readOrderDelegate = readOrderDelegate;
	}
	public CreateInvoice getCreateInvoiceDelegate() {
		return createInvoiceDelegate;
	}
	public void setCreateInvoiceDelegate(CreateInvoice createInvoiceDelegate) {
		this.createInvoiceDelegate = createInvoiceDelegate;
	}
	public ReadOrderProcessData getReadOrderProcessDataDelegate() {
		return readOrderProcessDataDelegate;
	}
	public void setReadOrderProcessDataDelegate(
			ReadOrderProcessData readOrderProcessDataDelegate) {
		this.readOrderProcessDataDelegate = readOrderProcessDataDelegate;
	}
	public CreateOrderProcessData getCreateOrderProcessDataDelegate() {
		return createOrderProcessDataDelegate;
	}
	public void setCreateOrderProcessDataDelegate(
			CreateOrderProcessData createOrderProcessDataDelegate) {
		this.createOrderProcessDataDelegate = createOrderProcessDataDelegate;
	}



	

	
	

}
