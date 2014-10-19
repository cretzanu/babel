package com.babel.order.process;

public class OrderProcessOverLocalEJBFactory extends OrderProcessOverRemoteEJBFactory{

	protected OrderProcessOverLocalEJBFactory(){
		super("local");
	}
}
