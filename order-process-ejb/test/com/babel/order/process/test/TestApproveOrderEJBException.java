package com.babel.order.process.test;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.babel.order.Order;
import com.babel.order.ReadOrder;
import com.babel.order.process.ApproveOrder;

/**
 * ApproveOrder is using the ioemail::SendEmailImpl that implements a constraint
 * on not null email addresses. We know that sending e-mail is the last step of
 * the AppproveOrder activity. According to the business logic, if the e-mail
 * cannot be sent, the system should rollback all previous updates (in
 * accounting, production and order). This test will simulate the scenario that
 * will trigger this exception. Then we have to check there is no new
 * record created in any of the tables from each databases (if you have configured for
 * multiple databases - the most realistic environment).
 */
public class TestApproveOrderEJBException {

	private static final long ORDER_ID = 7L;

	ApproveOrder approveOrderService;
	ReadOrder readOrderService;

	@Before
	public void init() throws NamingException {
		// lookup needs a JNDI name.
		// With JBoss AS 4.x, the default naming is EARFileName/ejb name/remote
		// (for remote access)
		approveOrderService = (ApproveOrder) new InitialContext()
				.lookup("order-process-ear/ApproveOrderEJB/remote");
		readOrderService = (ReadOrder) new InitialContext()
				.lookup("orderEAR/ReadOrderEJB/remote");
	}

	@Test
	public void testBusinessLogicException() {
		Order order = this.readOrderService.readOrder(ORDER_ID);
		// remove email address to trigger and exception in
		// ioemail:SendEmailImpl
		order.setCustomerEmail(null);
		this.approveOrderService.approveOrder(order);
		// TODO: implement the assestions on data created in Accounting,
		// Production
		// till then, please visualize the DB tables' data. No NEW record should
		// have been added as well as no updates in any of the tables as a
		// consequence of this test run.

	}
}
