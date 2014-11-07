package com.order.ejb.test;

import java.util.Date;

import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.babel.order.Order;

public class TestNewOrdersByMessageQueue {

	MessageProducer messageSender = null;
	Session session = null;

	@Before
	public void init() throws Exception {
		InitialContext ctx = new InitialContext();// jndi.properties is needed
													// in test root folder
		Queue queue = (Queue) ctx.lookup("/queue/NewOrdersQueue");
		QueueConnectionFactory factory = (QueueConnectionFactory) ctx
				.lookup("ConnectionFactory");
		QueueConnection connection = factory.createQueueConnection();
		session = connection.createQueueSession(false,
				QueueSession.AUTO_ACKNOWLEDGE);
		messageSender = ((QueueSession)session).createSender(queue);

	}

	@Test
	public void test() throws Exception{

		for (int i = 0; i < 3; i++) {
			Order order = new Order();
			order.setCustomerName("Customer " + i);
			order.setCustomerEmail("customer" + i + "@yahoo.com");
			order.setSpecialRequirements("by message queue at "
					+ new Date().getTime());
			//add some order lines if you like
			//now send this new order as a message
			ObjectMessage objectMessage = this.session.createObjectMessage(order);
			this.messageSender.send(objectMessage);
		}

	}
	@Test
	public void testNullEmailAddress() throws Exception{
		Order order= new Order(); order.setCustomerName("test null address");
		ObjectMessage objectMessage = this.session.createObjectMessage(order);
		this.messageSender.send(objectMessage);
		//should fail on non nullable email address (@see Order.emailAddress)
		//you should see 3 MDB listener attempts to process it, all failing
		//finally the order should end in the dead-letter queue 
	}

}
