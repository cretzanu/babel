package com.babel.order.rest;

import com.babel.order.CreateOrder;
import com.babel.order.ReadOrder;
import com.babel.order.SaveOrder;

public abstract class OrderFactory {

	public abstract CreateOrder createOrderFactory();

	public abstract ReadOrder readOrderFactory();

	public abstract SaveOrder saveOrderFactory();

	public static OrderFactory factory(){
		return new OrderImplFactory(); //returns local objects configured to manage transactions  
		//new OrderImplOverRemoteEJBFactory();//returns services
		
	}
}