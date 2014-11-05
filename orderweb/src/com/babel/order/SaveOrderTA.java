package com.babel.order;

public class SaveOrderTA extends SaveOrderImpl{
	
	@Override
	public Order saveOrder(Order p){
		this.getEm().getTransaction().begin();
		Order updatedOrder=super.saveOrder(p);
		this.getEm().getTransaction().commit();
		return updatedOrder;
	}
	
}
