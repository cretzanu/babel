package com.babel.order.batch.messaging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.babel.order.CreateOrder;
import com.babel.order.Order;

@MessageDriven(name = "NewOrderMessageHandlerSubscriber", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", 
				propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", 
				propertyValue = "/topic/NewOrdersTopic") })
public class NewOrderHandlerSubscriber implements MessageListener {
	@EJB
	CreateOrder createOrderDelegate;

	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = null;
		try {
			objectMessage = (ObjectMessage) message;
			Order order = (Order) objectMessage.getObject();
			createOrderDelegate.createOrder(order);

		} catch (Exception e) {
			e.printStackTrace();
			//TODO: error management
		}

	}

}
