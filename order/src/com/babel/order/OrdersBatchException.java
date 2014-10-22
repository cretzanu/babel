package com.babel.order;

import java.util.LinkedList;
import java.util.List;

public class OrdersBatchException extends Exception {
	
	List<Order> ordersInError=new LinkedList<Order>();

	public OrdersBatchException() {
		super();
	}
	public void addOrderInError(Order o){
		this.ordersInError.add(o);
	}
	@Override
	public String getMessage(){
		return "The folowing orders have errors:"+ordersInError.toArray();
	}

}
