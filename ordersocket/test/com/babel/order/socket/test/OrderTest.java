package com.babel.order.socket.test;

import org.junit.Before;
import org.junit.Test;

import com.babel.order.socket.CreateCSVOrder;
import com.babel.order.socket.OrderSocketFactory;


public class OrderTest {

	CreateCSVOrder createCSVOrderWorker;

	@Before
	public void init()  {
		createCSVOrderWorker=OrderSocketFactory.createCSVOrderFactory();

	}

	@Test
	public void test1() {
	StringBuilder msgBuilder=new StringBuilder().append("Customer 1")
			.append("\n").append("customer1@gmail.com")
		.append("\n").append("customer1 address")
		.append("\n").append("item1, 2, 100")
		.append("\n").append("item2, 10, 25");
	createCSVOrderWorker.createCSVOrder(msgBuilder.toString());
	
	
		
	}

}
