package com.babel.esb.impl;

import com.babel.order.Order;

/**
 * Simple bean to be used in various spring-integration tests
 * 
 * @author liviu.cretu
 * 
 */
public class TestHelper {

	public void sendOrderReceivedAcknEmail(Order order) {

		if (order.getCustomerEmail() == null) {

			throw new RuntimeException("there must be an e-mail address for order");
		}
		System.out.println("Sending e-mail to acknowledge order received from "
				+ order.getCustomerEmail());
	}
	public void sendOrderReceivedAlertEmail(Order order) {

		System.out
		.println(order.getCustomerName()
				+ "- new Order Received-->sending e-mail to the Order Process manager ");
	}
}
