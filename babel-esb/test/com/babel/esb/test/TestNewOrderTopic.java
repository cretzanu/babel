package com.babel.esb.test;

import java.util.Date;

import javax.jms.ObjectMessage;

import org.junit.Before;
import org.junit.Test;

import com.babel.esb.api.BabelGateway;
import com.babel.esb.api.OrderReceivedEvent;
import com.babel.esb.impl.BabelGatewayImpl;
import com.babel.order.Order;

public class TestNewOrderTopic {
	BabelGateway esb;

	@Before
	public void init() {
		this.esb = new BabelGatewayImpl();
		((BabelGatewayImpl) this.esb).init();
	}

	@Test
	public void test() throws Exception {

		for (int i = 0; i < 3; i++) {
			Order order = new Order();
			order.setCustomerName("Customer " + i);
			order.setCustomerEmail("customer" + i + "@yahoo.com");
			order.setSpecialRequirements("by message queue at "
					+ new Date().getTime());
			// add some order lines if you like
			// now send this new order as a message

			this.esb.handleMessage(new OrderReceivedEvent(order));
		}

	}

	@Test
	public void testNullEmailAddress() throws Exception {
		Order order = new Order();
		order.setCustomerName("test null address");
		this.esb.handleMessage(new OrderReceivedEvent(order));
		// some listeners should fail on non nullable email address (@see
		// Order.emailAddress)

	}

}
