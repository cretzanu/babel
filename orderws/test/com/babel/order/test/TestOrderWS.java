package com.babel.order.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.naming.NamingException;
import javax.xml.ws.BindingProvider;

import org.junit.Before;
import org.junit.Test;

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
	public void testService(){
		com.babel.order.test.ws.port.Order order=this.orderServiceDelegate.readOrder(4L);
		//for strange errors with jboss AS
		//https://issues.jboss.org/browse/JBWS-2418
		assertNotNull("Order not found",order);
				
		order.setSpecialRequirements("testing update"+new Date().getTime());
		com.babel.order.test.ws.port.Order updatedOrder=this.orderServiceDelegate.saveOrder(order);
		assertNotNull("Updated Order not coming back", updatedOrder);
		//this assertion is going to fail when OrderWS is workign with the OrderWSFactory
		//@see OrderWS constructor comment!!!
		assertTrue("Entity version should have changed +1 but it did not",
				order.getVersion().equals(updatedOrder.getVersion() - 1));
		
		
	}
}
