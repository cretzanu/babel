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

		// URLClassLoader classLoader =
		// (URLClassLoader)TestApproveOrder.class.getClassLoader();
		// System.out.println(Arrays.toString(classLoader.getURLs()));
		// IMPORTANT!!! when using JBoss4.2, delete
		// server/default/lib/spring2.5.jar
		// and replace it with the Spring version used in this project
		// (e.g. Spring-core-3.1.3-RELEASE.jar (from .m2/org/springfraemwork)

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
