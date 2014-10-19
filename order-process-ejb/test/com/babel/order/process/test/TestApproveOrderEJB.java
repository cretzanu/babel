package com.babel.order.process.test;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.babel.order.ReadOrder;
import com.babel.order.process.ApproveOrder;

public class TestApproveOrderEJB {

	private static final long ORDER_ID = 7L;

	ApproveOrder approveOrderService;
	

	@Before
	public void init() throws NamingException {
		// lookup needs a JNDI name.
		// With JBoss AS 4.x, the default naming is EARFileName/ejb name/remote
		// (for remote access)
		approveOrderService = (ApproveOrder) new InitialContext()
				.lookup("order-process-ear/ApproveOrderEJB/remote");

	}

	/**
	 * Testing ApproveOrderEJB - One single Distributed Transaction (all or
	 * nothing). This should create a new entry in OrderProcessData as well as
	 * in Invoice and ProductionPlan (simulating the other two systems). If
	 * ORDER_ID not changed, multiple runs of this test will duplicate related
	 * data for the same orderId (e.g, a new Invoice in accounting and a new
	 * ProductionPlan in production).For the sake of simplicity in testing and
	 * observing various scenarios, business logic has been limited to merely
	 * using various services/local implementations, depending on the
	 * configuration of the order-process-ejb::OrderProcessFactory).
	 * 
	 * Note: "javax.naming.NoInitialContextException:Cannot instantiate class
	 * org.jnp.interfaces.Naming..." may appear on first attempt to run this
	 * test. This may happen because there is no entry in pom for the app
	 * server-specific dependencies (e.g JBoss). The easiest way to fix it is to
	 * associate the project with the server runtime:
	 * Eclipse/order-process-ejb/properties/runtime environments/check the
	 * server you deploy on
	 */
	@Test
	public void testApproveById() {

		this.approveOrderService.approveOrder(ORDER_ID);
		// TODO: implement the assertions  on data created in Accounting, Production
		// Till then thiugh, please visualize the DB tables' data.

	}
}
	
