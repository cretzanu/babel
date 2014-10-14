package com.babel.order.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.persistence.Persistence;

import com.babel.order.CreateOrder;
import com.babel.order.CreateOrderImpl;
import com.babel.order.Order;
import com.babel.order.ReadOrder;
import com.babel.order.ReadOrderImpl;
import com.babel.order.SaveOrder;
import com.babel.order.SaveOrderImpl;

/**
 * The class implements Facade pattern to expose all Order interfaces as Web Service operations
 *
 * @author liviu.cretu
 * @version 1.0F
 * @created 06-Oct-2014 7:34:55 PM
 */
@WebService
@SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.RPC)//ok, this will be deprecated, eventually
public class OrderWS implements CreateOrder, ReadOrder, SaveOrder {

	private CreateOrder createOrderDelegate;
	private ReadOrder readOrderDelegate;
	private SaveOrder saveOrderDelegate;
	
	

	public OrderWS(){
		//IMPORTANT: Be aware of the transaction management here!
		//@see the getInstance comment!
		this.createOrderDelegate=OrderImplFactory.getInstance().createOrderFactory();
		this.readOrderDelegate=OrderImplFactory.getInstance().readOrderFactory();
		this.saveOrderDelegate=OrderImplFactory.getInstance().saveOrderFactory();
		
		
		
	}

	
	@Override
	@WebMethod
	public Order createOrder(Order p){
	    
		return this.createOrderDelegate.createOrder(p);
	}
	@Override
	@WebMethod
	public Order saveOrder(Order p) {
		return this.saveOrderDelegate.saveOrder(p);
	}

	@Override
	@WebMethod
	public Order readOrder(Long p) {
		return this.readOrderDelegate.readOrder(p);//new Order();
	}
}