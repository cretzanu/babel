package com.babel.order;

public class CreateOrderTA extends CreateOrderImpl{
	@Override
	public Order createOrder(Order p){
		this.getEm().getTransaction().begin();
		Order createdOrder=super.createOrder(p);
		this.getEm().getTransaction().commit();
		return createdOrder;
	}

}
