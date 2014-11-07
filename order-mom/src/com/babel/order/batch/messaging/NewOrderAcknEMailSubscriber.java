package com.babel.order.batch.messaging;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.SessionContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.babel.order.Order;

@MessageDriven(name = "NewOrderAcknEMailSubscriber", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", 
				propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", 
				propertyValue = "/topic/NewOrdersTopic") })
public class NewOrderAcknEMailSubscriber implements MessageListener {
	@Resource
	SessionContext session;
	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = null;
		try {
			objectMessage = (ObjectMessage) message;
			Order order = (Order) objectMessage.getObject();
			if (order.getCustomerEmail() == null)
				{
				session.setRollbackOnly(); //!!!
				throw new BusinessRuleException(
						"there must be an e-mail address for order"
								+ message.getJMSCorrelationID());
				}
			System.out
					.println("Sending e-mail to acknowledge order received from "
							+ order.getCustomerEmail());


		} catch (Exception e) {
			e.printStackTrace();
			// TODO: error management
		}

	}

}
