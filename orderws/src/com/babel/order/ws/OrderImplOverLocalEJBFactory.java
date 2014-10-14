package com.babel.order.ws;

public class OrderImplOverLocalEJBFactory extends OrderImplOverRemoteEJBFactory{

	protected OrderImplOverLocalEJBFactory(){
		super("local");
	}
}
