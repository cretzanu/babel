package com.babel.order.test;

import static org.junit.Assert.assertNotNull;

import javax.naming.NamingException;
import javax.xml.ws.BindingProvider;

import org.junit.Before;
import org.junit.Test;

import com.babel.order.Order;
import com.babel.order.test.ws.port.OrderWS;
import com.babel.order.test.ws.port.OrderWSService;

public class TestOrderWS {

	OrderWS orderServiceDelegate;
	
	@Before
	public void init() throws NamingException{
		OrderWSService proxy=new OrderWSService();
		orderServiceDelegate=proxy.getOrderWSPort();
		((BindingProvider)orderServiceDelegate).getRequestContext().put(BindingProvider.
	            ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/order-ws/Order");
	}
	@Test
	public void testRead(){
		com.babel.order.test.ws.port.Order order=this.orderServiceDelegate.readOrder(4L);
		//for strange errors with jboss AS
		//https://issues.jboss.org/browse/JBWS-2418
		assertNotNull("Order not found",order);
		
		
	}
}
