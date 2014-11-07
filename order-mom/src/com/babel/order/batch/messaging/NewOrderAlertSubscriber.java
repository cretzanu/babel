package com.babel.order.batch.messaging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.babel.order.Order;

@MessageDriven(name = "NewOrderAlertSubscriber", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", 
				propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", 
				propertyValue = "/topic/NewOrdersTopic") })
public class NewOrderAlertSubscriber implements MessageListener {

	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = null;
		try {
			objectMessage = (ObjectMessage) message;
			Order order = (Order) objectMessage.getObject();
			System.out
					.println(order.getCustomerName()
							+ "- new Order Received-->sending e-mail to the Order Process manager ");
			// TODO send e-mail to the manager
			// to acknowledge the receipt of the order;
			// use the ioemail component

		} catch (JMSException e) {
			e.printStackTrace();
			//TODO: error management
		}

	}

}
