package com.babel.order.rest;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.babel.order.CreateOrder;
import com.babel.order.ReadOrder;
import com.babel.order.SaveOrder;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 14-Oct-2014 10:41:03 AM
 */
public class OrderFactory {

	public OrderFactory() {

	}

	public void finalize() throws Throwable {

	}

	public static CreateOrder CreateOrderFactory() {
		try {
			return (CreateOrder) new InitialContext()
					.lookup("orderEAR(order-ear)/CreateOrderEJB/remote");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("server component not available");
		}
	}

	public static ReadOrder ReadOrderFactory() {
		try {
			return (ReadOrder) new InitialContext()
					.lookup("orderEAR(order-ear)/ReadOrderEJB/remote");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("server component not available");
		}
	}

	public static SaveOrder SaveOrderFactory() {
		try {
			return (SaveOrder) new InitialContext()
					.lookup("orderEAR(order-ear)/SaveOrderEJB/remote");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("server component not available");
		}
	}
}// end OrderFactory