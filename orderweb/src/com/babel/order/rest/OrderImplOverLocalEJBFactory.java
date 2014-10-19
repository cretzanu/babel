package com.babel.order.rest;

public class OrderImplOverLocalEJBFactory extends OrderImplOverRemoteEJBFactory{

	protected OrderImplOverLocalEJBFactory(){
		super("local");
	}
}
