package com.order.ejb.test;

import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.InitialContext;

import org.junit.Before;

/**
 * This test uses the NewOrdersTopic topic instead of NewOrdersQueue queue. The
 * topic has 3 listeners. Notice that at least one will fail
 * (NewOrderMessageTopicListener) for sure, while some of the others may execute
 * successfully! You should be aware of this behavior when working with topics
 * (publish/subscribe). The test will be green as long as the message has been
 * successfully sent to the topic.
 * 
 * @author liviu.cretu
 * 
 */
public class TestNewOrdersByMessageTopic extends TestNewOrdersByMessageQueue {

	@Before
	@Override
	public void init() throws Exception {
		InitialContext ctx = new InitialContext();// jndi.properties is needed
													// in test root folder
		Topic queue = (Topic) ctx.lookup("/topic/NewOrdersTopic");
		TopicConnectionFactory factory = (TopicConnectionFactory) ctx
				.lookup("ConnectionFactory");
		TopicConnection connection = factory.createTopicConnection();
		session = connection.createTopicSession(false,
				TopicSession.AUTO_ACKNOWLEDGE);
		messageSender = ((TopicSession) session).createProducer(queue);

	}

}
