package com.babel.order.socket.test;

import org.junit.Before;
import org.junit.Test;

import com.babel.order.socket.CreateCSVOrder;
import com.babel.order.socket.OrderSocketFactory;

/**
 * Test for the business logic only: create a new order from text implementing
 * the CreateCSVOrder protocol. No client-server Socket yet.
 * 
 * @author liviu.cretu
 * 
 */
public class CreateCSVOrderTest {

	CreateCSVOrder createCSVOrderWorker;

	@Before
	public void init() {
		createCSVOrderWorker = OrderSocketFactory.createCSVOrderFactory();

	}

	@Test
	public void test1() {
		String msg = buildNewOrderCSVText();
		createCSVOrderWorker.createCSVOrder(msg);
		//Expecting just to execute successfully
		

	}

	public static String buildNewOrderCSVText() {
		StringBuilder msgBuilder = new StringBuilder().append("Customer 1")
				.append("\n").append("customer1@gmail.com").append("\n")
				.append("customer1 address").append("\n")
				.append("item1, 2, 100").append("\n").append("item2, 10, 25").append("\n");
		return msgBuilder.toString();
	}

}
