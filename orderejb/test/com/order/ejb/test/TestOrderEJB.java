package com.order.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.babel.order.CreateOrder;
import com.babel.order.Order;
import com.babel.order.ReadOrder;
import com.babel.order.SaveOrder;

public class TestOrderEJB {

	private static final String TESTING_UPDATE = "testing update"
			+ Calendar.getInstance().getTimeInMillis();
	private static final long ORDER_ID = 4L;
	CreateOrder createOrderService;
	SaveOrder saveOrderService;
	ReadOrder readOrderService;

	@Before
	public void init() throws NamingException {
		// lookup needs a JNDI name.
		// With JBoss AS 4.x, the default naming is EARFileName/ejb name/remote
		// (for remote access)
		readOrderService = (ReadOrder) new InitialContext()
				.lookup("order-ear/ReadOrderEJB/remote");
		saveOrderService = (SaveOrder) new InitialContext()
				.lookup("order-ear/SaveOrderEJB/remote");
	}

	@Test
	public void testReadUpdate() {
		Order order = this.readOrderService.readOrder(ORDER_ID);
		assertNotNull("Order not found", order);
		order.setSpecialRequirements(TESTING_UPDATE);
		Order updatedOrder = this.saveOrderService.saveOrder(order);
		assertNotNull("Updated Order not coming back", updatedOrder);
		assertTrue("Entity version should have changed +1 but it did not",
				order.getVersion().equals(updatedOrder.getVersion() - 1));
		Order dbOrder = this.readOrderService.readOrder(ORDER_ID);
		assertTrue("the new field value not commited in DB",
				TESTING_UPDATE.equals(dbOrder.getSpecialRequirements()));

		assertTrue(
				"Entity version in the DB " + dbOrder.getVersion()
						+ " not the same as update return "
						+ updatedOrder.getVersion(), dbOrder.getVersion()
						.equals(updatedOrder.getVersion()));

	}

	@Test
	public void testNullParams() {
		Throwable expectedException=null;
		try {
			this.saveOrderService.saveOrder(null);
		} catch (Throwable e) {
			e.printStackTrace();
			//all serverside exceptions are wrapped around EJBException
			//so, get the real one (cause)
			expectedException=e.getCause();
				}
		assertNotNull("Expecting exception here. None received!", expectedException);
		assertTrue("An IllegalArgumentException expected." + expectedException.getClass()
				+ " received instead",
				expectedException.getClass().equals(IllegalArgumentException.class));

	}
}
